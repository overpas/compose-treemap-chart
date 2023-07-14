import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    alias(libs.plugins.detekt)
    id("publication")
}

group = properties["lib.group"].toString()
version = properties["lib.version"].toString()

println("group = $group, version = $version")

android {
    namespace = "by.overpass.treemapchart.compose"
    compileSdk = properties["android.compileSdk"].toString().toInt()
    defaultConfig {
        minSdk = properties["android.minSdk"].toString().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
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

@OptIn(
    ExperimentalKotlinGradlePluginApi::class,
)
kotlin {

    jvmToolchain(17)

    targetHierarchy.default()

    jvm("desktop")
    android {
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":treemap-chart"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui.test.manifest)
                implementation(libs.compose.ui.tooling)
            }
        }
        val iosMain by getting
        val jsMain by getting

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidUnitTest by getting
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.espresso.core)
                implementation(libs.compose.ui.test.junit4)
                implementation(libs.compose.ui.test.manifest)
                implementation(libs.compose.ui.tooling)
            }
        }
        val desktopTest by getting
        val iosTest by getting
    }
}

dependencies {
    detektPlugins(libs.compose.detekt.rules)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}