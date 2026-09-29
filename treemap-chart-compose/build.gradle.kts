import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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
        namespace = "by.overpass.treemapchart.compose"
        compileSdk = properties["android.compileSdk"].toString().toInt()
        minSdk = properties["android.minSdk"].toString().toInt()
        withHostTest {}
        withDeviceTest {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
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
            implementation(project(":treemap-chart"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material)
            implementation(libs.compose.ui.tooling.preview)
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.compose.desktop)
            }
        }
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.compose.ui.test)
        }
        getByName("androidDeviceTest").dependencies {
            implementation(libs.androidx.test.ext.junit)
            implementation(libs.androidx.test.espresso.core)
            implementation(libs.androidx.compose.ui.test.junit4)
            implementation(libs.androidx.compose.ui.test.manifest)
        }
    }
}

dependencies {
    detektPlugins(libs.compose.detekt.rules)
}

composeCompiler {
    stabilityConfigurationFiles.add(project.layout.projectDirectory.file("stability.conf"))
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    metricsDestination = layout.buildDirectory.dir("compose_compiler")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(properties["jvm.version"].toString())
    }
}
