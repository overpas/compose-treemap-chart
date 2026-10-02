plugins {
    id("publish")
    id("static-analysis")
    alias(libs.plugins.android.lib)
}

group = properties["lib.group"].toString()
version = properties["lib.version"].toString()

val jvmVersion = properties["jvm.version"].toString()

android {
    namespace = "by.overpass.treemapchart.view"
    compileSdk = properties["android.compileSdk"].toString().toInt()
    defaultConfig {
        minSdk = properties["android.minSdk"].toString().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(jvmVersion)
        targetCompatibility = JavaVersion.toVersion(jvmVersion)
    }
}

dependencies {
    implementation(project(":treemap-chart"))
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
