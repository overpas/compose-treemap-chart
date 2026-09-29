import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.lib)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
    id("publish")
}

group = properties["lib.group"].toString()
version = properties["lib.version"].toString()

kotlin {
    applyDefaultHierarchyTemplate()

    jvm("desktop")
    android {
        namespace = "by.overpass.treemapchart.core"
        compileSdk = properties["android.compileSdk"].toString().toInt()
        minSdk = properties["android.minSdk"].toString().toInt()
        withHostTest {}
    }
    iosArm64()
    iosSimulatorArm64()
    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            api(libs.kotlinx.collections.immutable)
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.compose.desktop)
            }
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

dependencies {
    detektPlugins(libs.compose.detekt.rules)
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(properties["jvm.version"].toString())
    }
}

composeCompiler {
    stabilityConfigurationFiles.add(project.layout.projectDirectory.file("stability.conf"))
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    metricsDestination = layout.buildDirectory.dir("compose_compiler")
}

tasks.register("commonUnitTest") {
    dependsOn("testAndroidHostTest", "desktopTest", "iosSimulatorArm64Test")
}
