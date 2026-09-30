object MavenCentral {
    const val STAGING_REPOSITORY_NAME = "mavenCentralStaging"
    const val STAGING_PUBLISH_TASK_SUFFIX = "ToMavenCentralStagingRepository"
    const val STAGING_PUBLISH_ALL_TASK = "publishAllPublications$STAGING_PUBLISH_TASK_SUFFIX"
    const val STAGING_DIRECTORY = "maven-central-staging"
    const val CLEAN_STAGING_TASK = "cleanMavenCentralStaging"
    const val PUBLISH_PLUGIN_ID = "publish"
    const val PORTAL_API_URL = "https://central.sonatype.com/api/v1/publisher"
    const val PORTAL_DEPLOYMENTS_URL = "https://central.sonatype.com/publishing/deployments"
}
