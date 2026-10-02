plugins {
    `maven-publish`
    signing
}

publishing {
    repositories {
        maven {
            name = MavenCentral.STAGING_REPOSITORY_NAME
            url = uri(rootProject.layout.buildDirectory.dir(MavenCentral.STAGING_DIRECTORY))
        }
    }

    publications.withType<MavenPublication>().configureEach {
        val publicationName = name
        val javadocJar = tasks.register<Jar>("${publicationName}JavadocJar") {
            archiveClassifier.set("javadoc")
            destinationDirectory.set(layout.buildDirectory.dir("javadoc/$publicationName"))
        }
        artifact(javadocJar)

        pom {
            name.set("Treemap Chart Compose")
            description.set("Jetpack Compose (Multiplatform) treemap chart implementation")
            url.set("https://github.com/overpas/compose-treemap-chart")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://github.com/overpas/compose-treemap-chart/blob/master/LICENSE.txt")
                }
            }

            developers {
                developer {
                    id.set("overpas")
                    name.set("Pavel Shurmilov")
                    email.set("pckeycalculator@gmail.com")
                }
            }

            scm {
                connection.set("scm:git:github.com/overpas/compose-treemap-chart.git")
                developerConnection.set("scm:git:ssh://github.com/overpas/compose-treemap-chart.git")
                url.set("https://github.com/overpas/compose-treemap-chart/tree/master")
            }
        }
    }
}

val signingKey = providers.gradleProperty("signingInMemoryKey")

signing {
    if (signingKey.isPresent) {
        useInMemoryPgpKeys(
            providers.gradleProperty("signingInMemoryKeyId").orNull,
            signingKey.get(),
            providers.gradleProperty("signingInMemoryKeyPassword").orNull,
        )
    }
    isRequired = signingKey.isPresent || providers.gradleProperty("signing.keyId").isPresent
    sign(publishing.publications)
}

tasks.withType<PublishToMavenRepository>()
    .matching { it.name.endsWith(MavenCentral.STAGING_PUBLISH_TASK_SUFFIX) }
    .configureEach {
        dependsOn(":${MavenCentral.CLEAN_STAGING_TASK}")
    }
