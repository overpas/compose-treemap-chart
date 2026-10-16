plugins {
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.cocoapods).apply(false)
    alias(libs.plugins.android.app).apply(false)
    alias(libs.plugins.android.lib).apply(false)
    alias(libs.plugins.android.test).apply(false)
    alias(libs.plugins.android.kmp.lib).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.kotlinx.kover)
    id("maven-central")
}

dependencies {
    kover(project(":treemap-chart"))
    kover(project(":treemap-chart-compose"))
    kover(project(":treemap-chart-view"))
}

tasks.register("cleanAll", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
