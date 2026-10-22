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
description = "Android Views treemap chart"

kotlin {
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()

    android {
        namespace = "by.overpass.treemapchart.views"
        compileSdk = properties["android.compileSdk"].toString().toInt()
        minSdk = properties["android.minSdk"].toString().toInt()
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        getByName("androidHostTest").dependencies {
            implementation(kotlin("test-junit"))
            implementation(libs.robolectric)
        }
        androidMain.dependencies {
            api(project(":treemap-chart"))
            implementation(libs.androidx.core.ktx)
        }
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(properties["jvm.version"].toString())
    }
}

tasks.withType<Test>().configureEach {
    jvmArgs("--add-exports=java.base/jdk.internal.access=ALL-UNNAMED")
}
