import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault
import java.io.IOException
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.time.Instant
import java.util.Base64
import java.util.UUID

@DisableCachingByDefault(because = "Uploads to a remote service")
abstract class UploadToMavenCentral : DefaultTask() {

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val bundle: RegularFileProperty

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val stagingDirectory: DirectoryProperty

    @get:Input
    abstract val groupId: Property<String>

    @get:Input
    abstract val version: Property<String>

    @get:Input
    abstract val publishingType: Property<String>

    @get:Input
    abstract val timeoutMinutes: Property<Int>

    @get:Internal
    abstract val username: Property<String>

    @get:Internal
    abstract val password: Property<String>

    private val client: HttpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(CONNECT_TIMEOUT_SECONDS))
        .build()

    @TaskAction
    fun upload() {
        val type = publishingType.get()
        if (type !in PUBLISHING_TYPES) {
            throw GradleException("mavenCentralPublishingType must be one of $PUBLISHING_TYPES, was '$type'")
        }
        if (!username.isPresent || !password.isPresent) {
            throw GradleException(
                "Set the mavenCentralUsername and mavenCentralPassword Gradle properties to a Central Portal user token",
            )
        }
        val errors = MavenCentralBundleValidator.validate(
            stagingDirectory = stagingDirectory.get().asFile,
            groupId = groupId.get(),
            version = version.get(),
            requireSignatures = true,
        )
        if (errors.isNotEmpty()) {
            throw GradleException(
                "Maven Central bundle is not valid:\n" + errors.joinToString("\n") { " - $it" },
            )
        }
        val authorization = "Bearer " + Base64.getEncoder()
            .encodeToString("${username.get()}:${password.get()}".toByteArray())
        checkNotPublished(authorization)
        val deploymentId = uploadBundle(authorization, type)
        logger.lifecycle("Uploaded deployment $deploymentId, see ${MavenCentral.PORTAL_DEPLOYMENTS_URL}")
        waitForDeployment(authorization, deploymentId, type)
    }

    private fun checkNotPublished(authorization: String) {
        val published = MavenCentralBundleValidator.artifactIds(stagingDirectory.get().asFile, groupId.get())
            .filter { artifactId ->
                val query = listOf(
                    "namespace" to groupId.get(),
                    "name" to artifactId,
                    "version" to version.get(),
                ).joinToString("&") { (key, value) -> "$key=${encode(value)}" }
                val request = HttpRequest.newBuilder(URI("${MavenCentral.PORTAL_API_URL}/published?$query"))
                    .header("Authorization", authorization)
                    .timeout(Duration.ofSeconds(REQUEST_TIMEOUT_SECONDS))
                    .GET()
                    .build()
                val response = send(request)
                PUBLISHED_REGEX.containsMatchIn(response.body())
            }
        if (published.isNotEmpty()) {
            throw GradleException(
                "Version ${version.get()} is already on Maven Central for ${published.joinToString()}. " +
                    "Increase lib.version in gradle.properties",
            )
        }
    }

    private fun uploadBundle(authorization: String, type: String): String {
        val boundary = "----${UUID.randomUUID()}"
        val file = bundle.get().asFile
        val header = "--$boundary\r\n" +
            "Content-Disposition: form-data; name=\"bundle\"; filename=\"${file.name}\"\r\n" +
            "Content-Type: application/octet-stream\r\n\r\n"
        val body = header.toByteArray() + file.readBytes() + "\r\n--$boundary--\r\n".toByteArray()
        val name = encode("${groupId.get()}:${version.get()}")
        val request = HttpRequest.newBuilder(
            URI("${MavenCentral.PORTAL_API_URL}/upload?name=$name&publishingType=$type"),
        )
            .header("Authorization", authorization)
            .header("Content-Type", "multipart/form-data; boundary=$boundary")
            .timeout(Duration.ofMinutes(UPLOAD_TIMEOUT_MINUTES))
            .POST(HttpRequest.BodyPublishers.ofByteArray(body))
            .build()
        return send(request).body().trim()
    }

    private fun waitForDeployment(authorization: String, deploymentId: String, type: String) {
        val successStates = if (type == USER_MANAGED) setOf("VALIDATED") else setOf("PUBLISHING", "PUBLISHED")
        val deadline = Instant.now().plus(Duration.ofMinutes(timeoutMinutes.get().toLong()))
        var lastState: String? = null
        var failedPolls = 0
        while (Instant.now().isBefore(deadline)) {
            Thread.sleep(POLL_INTERVAL.toMillis())
            val request = HttpRequest.newBuilder(URI("${MavenCentral.PORTAL_API_URL}/status?id=${encode(deploymentId)}"))
                .header("Authorization", authorization)
                .timeout(Duration.ofSeconds(REQUEST_TIMEOUT_SECONDS))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build()
            val body = try {
                send(request).body()
            } catch (exception: GradleException) {
                failedPolls++
                if (failedPolls > MAX_FAILED_POLLS) throw exception
                logger.warn("Deployment status request failed, retrying: ${exception.message}")
                continue
            }
            failedPolls = 0
            val state = STATE_REGEX.find(body)?.groupValues?.get(1)
            if (state != lastState) {
                logger.lifecycle("Deployment $deploymentId is $state")
                lastState = state
            }
            when (state) {
                in successStates -> return
                "FAILED" -> throw GradleException("Deployment $deploymentId failed:\n$body")
            }
        }
        throw GradleException(
            "Deployment $deploymentId is still $lastState after ${timeoutMinutes.get()} minutes. " +
                "Check ${MavenCentral.PORTAL_DEPLOYMENTS_URL} before you upload again",
        )
    }

    private fun send(request: HttpRequest): HttpResponse<String> {
        val response = try {
            client.send(request, HttpResponse.BodyHandlers.ofString())
        } catch (exception: IOException) {
            throw GradleException("${request.method()} ${request.uri()} failed: ${exception.message}", exception)
        }
        if (response.statusCode() !in SUCCESS_CODES) {
            throw GradleException(
                "${request.method()} ${request.uri()} returned ${response.statusCode()}: ${response.body()}",
            )
        }
        return response
    }

    private fun encode(value: String): String = URLEncoder.encode(value, Charsets.UTF_8)

    private companion object {
        const val USER_MANAGED = "USER_MANAGED"
        val PUBLISHING_TYPES = setOf("AUTOMATIC", USER_MANAGED)
        val SUCCESS_CODES = 200..299
        val STATE_REGEX = Regex("\"deploymentState\"\\s*:\\s*\"(\\w+)\"")
        val PUBLISHED_REGEX = Regex("\"published\"\\s*:\\s*true")
        val POLL_INTERVAL: Duration = Duration.ofSeconds(15)
        const val MAX_FAILED_POLLS = 5
        const val CONNECT_TIMEOUT_SECONDS = 30L
        const val REQUEST_TIMEOUT_SECONDS = 60L
        const val UPLOAD_TIMEOUT_MINUTES = 10L
    }
}
