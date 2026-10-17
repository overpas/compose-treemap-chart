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
Every direct child of `TreemapChart` takes an area in proportion to its `Modifier.weight`. Put a
`TreemapChart` in another `TreemapChart` to make a group:
```kotlin
@Composable
fun MyChart() {
    TreemapChart(Modifier.fillMaxSize()) {
        TreemapChart(Modifier.weight(6.0)) {
            SimpleTreemapItem(item = "4", modifier = Modifier.weight(4.0))
            TreemapChart(Modifier.weight(2.0)) {
                SimpleTreemapItem(item = "1", modifier = Modifier.weight(1.0))
                SimpleTreemapItem(item = "1", modifier = Modifier.weight(1.0))
            }
        }
        TreemapChart(Modifier.weight(3.0)) {
            SimpleTreemapItem(item = "2", modifier = Modifier.weight(2.0))
            SimpleTreemapItem(item = "1", modifier = Modifier.weight(1.0))
        }
        SimpleTreemapItem(item = "1", modifier = Modifier.weight(1.0))
    }
}

@Composable
fun SimpleTreemapItem(
    item: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.border(1.dp, MaterialTheme.colors.onBackground),
    ) {
        Text(
            text = item,
            textAlign = TextAlign.Center,
        )
    }
}
```
The code above produces something like this:

<img src="https://raw.githubusercontent.com/overpas/compose-treemap-chart/master/img/sample_treemap.png" width="750">

The `treemap-chart` artifact alone has no Compose dependency. It gives the `tree {}` model and the
layout algorithms (`SquarifiedMeasurer`, `SliceAndDiceMeasurer`) for other UI toolkits.

For more advanced Kotlin Multiplatform samples check out the [sample](https://github.com/overpas/compose-treemap-chart/tree/master/sample) directory

## Development
Set the git hooks path to `config/git-hooks` to run detekt before each commit:
```shell
git config --local core.hooksPath config/git-hooks
```
