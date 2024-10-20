plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
}

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
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
        //        create("benchmark") {
//            initWith(release)
//            signingConfig = signingConfigs.getByName("debug")
//            matchingFallbacks.add("release")
//            proguardFiles("benchmark-rules.pro")
//        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(properties["jvm.version"].toString())
        targetCompatibility = JavaVersion.toVersion(properties["jvm.version"].toString())
    }
}

dependencies {
    implementation(project(":sample:shared"))
    implementation(compose.ui)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.uiTooling)
    implementation(compose.components.uiToolingPreview)
    implementation(libs.activity.compose)
    implementation(libs.androidx.profile.installer)
    debugImplementation(libs.compose.runtime.tracing)
    detektPlugins(libs.compose.detekt.rules)
}