val stagingDir = layout.buildDirectory.dir(MavenCentral.STAGING_DIRECTORY)
val libGroup = providers.gradleProperty("lib.group")
val libVersion = providers.gradleProperty("lib.version")

tasks.register<Delete>(MavenCentral.CLEAN_STAGING_TASK) {
    delete(stagingDir)
}

val publishToStaging = tasks.register("publishToMavenCentralStaging") {
    group = PublishingPlugin.PUBLISH_TASK_GROUP
    description = "Publishes all library publications to the local Maven Central staging directory"
    dependsOn(
        provider {
            subprojects
                .filter { it.plugins.hasPlugin(MavenCentral.PUBLISH_PLUGIN_ID) }
                .map { "${it.path}:${MavenCentral.STAGING_PUBLISH_ALL_TASK}" }
                .ifEmpty { throw GradleException("No project applies the ${MavenCentral.PUBLISH_PLUGIN_ID} plugin") }
        },
    )
}

val validateBundle = tasks.register<ValidateMavenCentralBundle>("validateMavenCentralBundle") {
    group = PublishingPlugin.PUBLISH_TASK_GROUP
    description = "Checks that the staged publications meet the Maven Central requirements"
    dependsOn(publishToStaging)
    stagingDirectory.set(stagingDir)
    groupId.set(libGroup)
    version.set(libVersion)
    requireSignatures.set(
        providers.gradleProperty("signingInMemoryKey")
            .orElse(providers.gradleProperty("signing.keyId"))
            .map { true }
            .orElse(false),
    )
}

val zipBundle = tasks.register<Zip>("zipMavenCentralBundle") {
    group = PublishingPlugin.PUBLISH_TASK_GROUP
    description = "Creates the Maven Central Portal bundle from the staged publications"
    dependsOn(validateBundle)
    from(stagingDir)
    exclude("**/maven-metadata*")
    archiveFileName.set("maven-central-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("maven-central"))
}

tasks.register<UploadToMavenCentral>("publishToMavenCentral") {
    group = PublishingPlugin.PUBLISH_TASK_GROUP
    description = "Uploads the bundle to the Maven Central Portal and waits for the deployment result"
    bundle.set(zipBundle.flatMap { it.archiveFile })
    stagingDirectory.set(stagingDir)
    groupId.set(libGroup)
    version.set(libVersion)
    publishingType.set(providers.gradleProperty("mavenCentralPublishingType").orElse("AUTOMATIC"))
    timeoutMinutes.set(providers.gradleProperty("mavenCentralTimeoutMinutes").map(String::toInt).orElse(30))
    username.set(providers.gradleProperty("mavenCentralUsername"))
    password.set(providers.gradleProperty("mavenCentralPassword"))
}
