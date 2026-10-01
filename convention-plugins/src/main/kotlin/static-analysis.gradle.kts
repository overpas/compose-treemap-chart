import dev.detekt.gradle.Detekt
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("dev.detekt")
}

val libs = the<LibrariesForLibs>()

detekt {
    parallel = true
    buildUponDefaultConfig = true
    basePath = rootProject.layout.projectDirectory
    config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
    source.setFrom(layout.projectDirectory.dir("src"), layout.projectDirectory.file("build.gradle.kts"))
}

dependencies {
    detektPlugins(fileTree(rootProject.layout.projectDirectory.dir("config/detekt/plugins")) { include("*.jar") })

    detektPlugins(libs.detekt.compose.rules)
    detektPlugins(libs.detekt.ktlint.wrapper)
}

tasks.withType<Detekt>().configureEach {
    autoCorrect = true
    reports {
        html.required = true
        markdown.required = true
        sarif.required = false
        checkstyle.required = false
    }
}

val typeResolutionTasks = tasks.withType<Detekt>().matching {
    it.name != "detekt" && !it.name.endsWith("SourceSet")
}

typeResolutionTasks.configureEach {
    val buildDir = layout.buildDirectory.get().asFile
    autoCorrect = false
    buildUponDefaultConfig = false
    config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt-type-resolution.yml"))
    exclude { it.file.startsWith(buildDir) }
}

tasks.named("detekt") {
    dependsOn(typeResolutionTasks)
}
