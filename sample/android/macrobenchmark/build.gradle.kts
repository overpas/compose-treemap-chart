import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
    id("com.android.test")
    kotlin("android")
}

android {
    compileSdk = 34
    namespace = "by.overpass.treemapchart.sample.macrobenchmark"

    defaultConfig {
        minSdk = 26
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    testOptions {
        managedDevices {
            devices {
                create<ManagedVirtualDevice>("pixel2Api33") {
                    device = "Pixel 2"
                    apiLevel = 33
                    systemImageSource = "aosp"
                }
            }
        }
    }
    targetProjectPath = ":sample:android"
    experimentalProperties["android.experimental.self-instrumenting"] = true

    buildTypes {
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks.add("release")
        }
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(libs.macrobenchmark.junit)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.espresso.core)
    implementation(libs.ui.automator)
    implementation(libs.androidx.test.rules)
}
