plugins {
    id("static-analysis")
    alias(libs.plugins.android.app)
}

val jvmVersion = properties["jvm.version"].toString()

android {
    namespace = "by.overpass.treemapchart.sample.views"
    compileSdk = properties["android.compileSdk"].toString().toInt()
    defaultConfig {
        applicationId = "by.overpass.treemapchart.sample.views"
        minSdk = 26
        targetSdk = properties["android.targetSdk"].toString().toInt()
        versionCode = 1
        versionName = "1.0"
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
            proguardFiles("benchmark-rules.pro")
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
    implementation(project(":treemap-chart"))
    implementation(project(":treemap-chart-views"))
    implementation(libs.androidx.profile.installer)
}
