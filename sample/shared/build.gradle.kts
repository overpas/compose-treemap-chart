import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "by.overpass.treemapchart.sample.shared"
    compileSdk = properties["android.compileSdk"].toString().toInt()
    defaultConfig {
        minSdk = properties["android.minSdk"].toString().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(properties["jvm.version"].toString())
        targetCompatibility = JavaVersion.toVersion(properties["jvm.version"].toString())
    }
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs(
                "src/androidMain/res",
                "src/commonMain/resources",
            )
        }
    }
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
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
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
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.uiTooling)
            }
        }
        val commonJvmMain by creating {
            dependsOn(commonMain)
            desktopMain.dependsOn(this)
            androidMain.dependsOn(this)
        }
        val iosMain by getting
        val jsMain by getting {
            dependencies {
                implementation(libs.kotlin.wrappers.js)
            }
        }
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
        val androidUnitTest by getting
        val desktopTest by getting
        val iosTest by getting
    }
}

dependencies {
    detektPlugins(libs.compose.detekt.rules)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(properties["jvm.version"].toString())
    }
}
