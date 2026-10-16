plugins {
    id("static-analysis")
    alias(libs.plugins.android.test)
}

android {
    namespace = "by.overpass.treemapchart.benchmark"
    compileSdk = properties["android.compileSdk"].toString().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(properties["jvm.version"].toString())
        targetCompatibility = JavaVersion.toVersion(properties["jvm.version"].toString())
    }

    defaultConfig {
        minSdk = properties["android.minSdk"].toString().toInt()
        targetSdk = properties["android.targetSdk"].toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR"
    }

    buildTypes {
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
        }
    }

    targetProjectPath = ":sample:android"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
    implementation(libs.macrobenchmark.junit)
    implementation(libs.ui.automator)
    implementation(libs.androidx.test.ext.junit)
}

androidComponents {
    beforeVariants(selector().all()) {
        it.enable = it.buildType == "benchmark"
    }
}
