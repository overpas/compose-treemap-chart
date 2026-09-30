pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

rootProject.name = "compose-treemap-chart"
includeBuild("convention-plugins")
include(":treemap-chart")
include(":treemap-chart-compose")
include(":sample:shared")
include(":sample:android")
include(":sample:desktop")
include(":sample:web")
include(":sample:web-wasm")
include(":benchmark")
