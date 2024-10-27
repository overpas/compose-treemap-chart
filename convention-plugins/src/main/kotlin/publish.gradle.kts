import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    // Define coordinates for the published artifact
    coordinates(
        groupId = properties["lib.group"].toString().also { println(it) },
        artifactId = project.name.also { println(it) },
        version = properties["lib.version"].toString().also { println(it) },
    )

    // Configure POM metadata for the published artifact
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

        // Specify developers information
        developers {
            developer {
                id.set("overpas")
                name.set("Pavel Shurmilov")
                email.set("pckeycalculator@gmail.com")
            }
        }

        // Specify SCM information
        scm {
            connection.set("scm:git:github.com/overpas/compose-treemap-chart.git")
            developerConnection.set("scm:git:ssh://github.com/overpas/compose-treemap-chart.git")
            url.set("https://github.com/overpas/compose-treemap-chart/tree/master")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}
