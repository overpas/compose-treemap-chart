import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

@DisableCachingByDefault(because = "Validation is fast and produces no output")
abstract class ValidateMavenCentralBundle : DefaultTask() {

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val stagingDirectory: DirectoryProperty

    @get:Input
    abstract val groupId: Property<String>

    @get:Input
    abstract val version: Property<String>

    @get:Input
    abstract val requireSignatures: Property<Boolean>

    @TaskAction
    fun validate() {
        val errors = MavenCentralBundleValidator.validate(
            stagingDirectory = stagingDirectory.get().asFile,
            groupId = groupId.get(),
            version = version.get(),
            requireSignatures = requireSignatures.get(),
        )
        if (errors.isNotEmpty()) {
            throw GradleException(
                "Maven Central bundle is not valid:\n" + errors.joinToString("\n") { " - $it" },
            )
        }
        val artifactIds = MavenCentralBundleValidator.artifactIds(stagingDirectory.get().asFile, groupId.get())
        logger.lifecycle("Maven Central bundle is valid: ${artifactIds.joinToString()}")
    }
}
