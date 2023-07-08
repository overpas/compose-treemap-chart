import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    alias(libs.plugins.detekt)
}

@OptIn(
    ExperimentalKotlinGradlePluginApi::class,
)
kotlin {

    jvmToolchain(17)

    targetHierarchy.default()

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":sample:shared"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

dependencies {
    detektPlugins(libs.compose.detekt.rules)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}