import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("kotlin")
    alias(libs.plugins.detekt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation(libs.junit)
}

//ext {
//    PUBLISH_ARTIFACT_ID = "treemap-core"
//}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}