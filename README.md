# Compose Treemap Chart
![Maven Central](https://img.shields.io/maven-central/v/io.github.overpas/treemap-chart-compose)
[![Build](https://github.com/overpas/compose-treemap-chart/actions/workflows/build.yml/badge.svg)](https://github.com/overpas/compose-treemap-chart/actions/workflows/build.yml)
![Badge-Android](https://img.shields.io/badge/platform-android-blue)
![Badge-JVM](https://img.shields.io/badge/platform-jvm-blue)
![Badge-iOS](https://img.shields.io/badge/platform-ios-blue)
![Badge-JS](https://img.shields.io/badge/platform-js-blue)
![Badge-Wasm](https://img.shields.io/badge/platform-wasm-blue)

Jetpack compose [treemap](https://en.wikipedia.org/wiki/Treemapping) chart implementation
<img src="https://raw.githubusercontent.com/overpas/compose-treemap-chart/master/img/sample_complex_chart.png" width="500">

## Usage
### Adding to the project
Add this to your `dependencies` gradle block:
```gradle
implementation "io.github.overpas:treemap-chart:0.1.3"
implementation "io.github.overpas:treemap-chart-compose:0.1.3"
```
### Sample code
```kotlin
private val simpleTreeData = tree(10) {
    node(6) {
        node(4)
        node(2) {
            node(1)
            node(1)
        }
    }
    node(3) {
        node(2)
        node(1)
    }
    node(1)
}

@Composable
fun MyChart() {
    TreemapChart(
        data = simpleTreeData,
        evaluateItem = Int::toDouble,
        modifier = Modifier.fillMaxSize(),
    ) { item ->
        SimpleTreemapItem(item = item.toString())
    }
}
```
The code above produces something like this:
<img src="https://raw.githubusercontent.com/overpas/compose-treemap-chart/master/img/sample_treemap.png" width="750">

For more advanced Kotlin Multiplatform samples check out the [sample](https://github.com/overpas/compose-treemap-chart/tree/master/sample) directory
