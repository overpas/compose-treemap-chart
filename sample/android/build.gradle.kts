plugins {
    id("static-analysis")
    alias(libs.plugins.android.app)
    alias(libs.plugins.compose.compiler)
}

val jvmVersion = properties["jvm.version"].toString()

android {
    namespace = "by.overpass.treemapchart.sample.android"
    compileSdk = properties["android.compileSdk"].toString().toInt()
    defaultConfig {
        applicationId = "by.overpass.treemapchart.sample.android"
        minSdk = 26
        targetSdk = properties["android.targetSdk"].toString().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        val release = getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        create("benchmark") {
            initWith(release)
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf(release.name)
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(jvmVersion)
        targetCompatibility = JavaVersion.toVersion(jvmVersion)
    }
}

dependencies {
    implementation(project(":sample:shared"))
    implementation(project(":treemap-chart"))
    implementation(project(":treemap-chart-view"))
    implementation(libs.activity.compose)
    implementation(libs.androidx.profile.installer)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.runtime.tracing)
}
