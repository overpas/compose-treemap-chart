import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("publish")
    id("static-analysis")
    alias(libs.plugins.android.kmp.lib)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.kover)
}

group = properties["lib.group"].toString()
version = properties["lib.version"].toString()
description = "Treemap chart tree model and layout algorithms with no UI dependency"

kotlin {
    applyDefaultHierarchyTemplate()

    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()

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
            api(libs.kotlinx.collections.immutable)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(properties["jvm.version"].toString())
    }
}

tasks.register("commonUnitTest") {
    description = "Runs the common unit tests on the Android host, desktop and iOS simulator targets."
    dependsOn("testAndroidHostTest", "desktopTest", "iosSimulatorArm64Test")
}
