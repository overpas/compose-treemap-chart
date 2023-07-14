import java.util.Properties

plugins {
    `maven-publish`
    signing
}

// Stub secrets to let the project sync and build without the publication values set up
ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.key"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

// Grabbing secrets from local.properties file or from environment variables, which could be used on CI
val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply {
            load(it)
        }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.key"] = System.getenv("SIGNING_SECRET_KEY")
    ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    // Configure maven central repository
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }

    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
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

// Signing artifacts. Signing.* extra properties values will be used
signing {
    useInMemoryPgpKeys(
        project.ext["signing.keyId"]?.toString(),
        project.ext["signing.key"]?.toString(),
        project.ext["signing.password"]?.toString(),
    )
    sign(publishing.publications)
}

tasks.matching { it.name.endsWith("PublicationToSonatypeRepository") }
    .configureEach {
        dependsOn(tasks.matching { it.name == "signIosArm64Publication" })
        dependsOn(tasks.matching { it.name == "signIosX64Publication" })
        dependsOn(tasks.matching { it.name == "signIosSimulatorArm64Publication" })
        dependsOn(tasks.matching { it.name == "signDesktopPublication" })
        dependsOn(tasks.matching { it.name == "signJsPublication" })
        dependsOn(tasks.matching { it.name == "signKotlinMultiplatformPublication" })
        dependsOn(tasks.matching { it.name == "signAndroidDebugPublication" })
        dependsOn(tasks.matching { it.name == "signAndroidReleasePublication" })
    }
