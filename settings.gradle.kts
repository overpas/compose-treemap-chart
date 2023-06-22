pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

rootProject.name = "compose-treemap-chart"

include(":treemap-example")
include(":treemap-core")
include(":treemap-compose-android")
