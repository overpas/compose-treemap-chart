import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.lib)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    cocoapods {
        summary = "Treemap Chart Compose Multiplatform sample"
        homepage = "https://github.com/overpas/compose-treemap-chart"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            isStatic = true
            baseName = "Shared"
        }
    }

    applyDefaultHierarchyTemplate()

    jvm("desktop")
    android {
        namespace = "by.overpass.treemapchart.sample.shared"
        compileSdk = properties["android.compileSdk"].toString().toInt()
        minSdk = properties["android.minSdk"].toString().toInt()
    }
    iosArm64()
    iosSimulatorArm64()
    js {
        browser()
        // Compose's JS test check needs a webpack bundle to load the Skiko runtime (CMP-4906)
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":treemap-chart"))
                implementation(project(":treemap-chart-compose"))
                implementation(libs.compose.runtime)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.compose.desktop)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui.tooling)
            }
        }
        val commonJvmMain by creating {
            dependsOn(commonMain)
            desktopMain.dependsOn(this)
            androidMain.dependsOn(this)
        }
        val iosMain by getting
        val jsMain by getting
        val wasmJsMain by getting
        val nonAndroidMain by creating {
            dependsOn(commonMain)
            desktopMain.dependsOn(this)
            iosMain.dependsOn(this)
            jsMain.dependsOn(this)
            wasmJsMain.dependsOn(this)
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
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
