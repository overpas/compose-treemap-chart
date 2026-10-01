import org.gradle.plugin.use.PluginDependency

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.maven.publish.plugin)
    implementation(libs.plugins.detekt.toDep())
    implementation(libs.plugins.kotlin.multiplatform.toDep())
}

fun Provider<PluginDependency>.toDep() = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}
