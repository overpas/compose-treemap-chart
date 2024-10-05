pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        mavenLocal()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "compose-treemap-chart"
includeBuild("convention-plugins")
include(":treemap-chart")
include(":treemap-chart-compose")
include(":sample:shared")
include(":sample:android")
//include(":sample:android:macrobenchmark")
include(":sample:desktop")
include(":sample:web")
include(":sample:web-wasm")
