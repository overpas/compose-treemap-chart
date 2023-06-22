plugins {
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.android.lib).apply(false)
    alias(libs.plugins.android.app).apply(false)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.nexus.publish)
    alias(libs.plugins.dokka)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}