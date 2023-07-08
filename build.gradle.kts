import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.android.lib).apply(false)
    alias(libs.plugins.android.app).apply(false)
    alias(libs.plugins.compose.multiplatform).apply(false)
    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.nexus.publish)
    alias(libs.plugins.dokka)
}

tasks.register("cleanAll", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("detektAll") {
    allprojects {
        this@register.dependsOn(tasks.withType<Detekt>())
    }
}
