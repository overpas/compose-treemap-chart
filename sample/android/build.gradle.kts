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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(libs.activity.compose)
    implementation(libs.androidx.profile.installer)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.runtime.tracing)
    androidTestImplementation(project(":treemap-chart"))
    androidTestImplementation(project(":treemap-chart-compose"))
    androidTestImplementation(project(":treemap-chart-view"))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
