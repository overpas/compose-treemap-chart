import java.io.File

object MavenCentralBundleValidator {

    private val checksumExtensions = listOf("md5", "sha1")
    private val derivedExtensions = listOf("asc", "md5", "sha1", "sha256", "sha512")
    private val requiredPomElements = listOf(
        "<name>",
        "<description>",
        "<url>",
        "<license>",
        "<developer>",
        "<scm>",
    )

    fun artifactIds(stagingDirectory: File, groupId: String): List<String> =
        groupDirectory(stagingDirectory, groupId)
            .listFiles(File::isDirectory)
            .orEmpty()
            .map(File::getName)
            .sorted()

    fun validate(
        stagingDirectory: File,
        groupId: String,
        version: String,
        requireSignatures: Boolean,
    ): List<String> {
        val errors = mutableListOf<String>()
        if (version.isBlank() || version.endsWith("-SNAPSHOT")) {
            errors += "Version '$version' can not be published to Maven Central"
        }
        val artifactIds = artifactIds(stagingDirectory, groupId)
        if (artifactIds.isEmpty()) {
            errors += "No artifacts for group '$groupId' in $stagingDirectory"
        }
        artifactIds.forEach { artifactId ->
            errors += validateArtifact(
                artifactDirectory = groupDirectory(stagingDirectory, groupId).resolve(artifactId),
                artifactId = artifactId,
                version = version,
                requireSignatures = requireSignatures,
            )
        }
        return errors
    }

    private fun groupDirectory(stagingDirectory: File, groupId: String): File =
        stagingDirectory.resolve(groupId.replace('.', '/'))

    private fun validateArtifact(
        artifactDirectory: File,
        artifactId: String,
        version: String,
        requireSignatures: Boolean,
    ): List<String> {
        val errors = mutableListOf<String>()
        val versions = artifactDirectory.listFiles(File::isDirectory).orEmpty().map(File::getName)
        if (versions != listOf(version)) {
            errors += "$artifactId: expected only version '$version', found $versions"
        }
        val versionDirectory = artifactDirectory.resolve(version)
        val baseName = "$artifactId-$version"
        val pom = versionDirectory.resolve("$baseName.pom")
        if (!pom.isFile) {
            return errors + "$artifactId: missing ${pom.name}"
        }
        val pomText = pom.readText()
        requiredPomElements.filterNot(pomText::contains).forEach { element ->
            errors += "$artifactId: ${pom.name} has no $element element"
        }
        listOf("sources", "javadoc").forEach { classifier ->
            if (!versionDirectory.resolve("$baseName-$classifier.jar").isFile) {
                errors += "$artifactId: missing $baseName-$classifier.jar"
            }
        }
        val files = versionDirectory.listFiles(File::isFile).orEmpty()
        val names = files.map(File::getName).toSet()
        files.map(File::getName)
            .filterNot { name -> derivedExtensions.any { name.endsWith(".$it") } }
            .forEach { name ->
                checksumExtensions.filterNot { "$name.$it" in names }.forEach { extension ->
                    errors += "$artifactId: missing $name.$extension"
                }
                if (requireSignatures && "$name.asc" !in names) {
                    errors += "$artifactId: missing signature $name.asc"
                }
            }
        return errors
    }
}
