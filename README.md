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
implementation "io.github.overpas:treemap-chart-compose:0.2.0"
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

<img src="https://raw.githubusercontent.com/overpas/compose-treemap-chart/master/img/sample_treemap.png" width="500">

### Canvas chart
`CanvasTreemapChart` draws all nodes of a `Tree` on one canvas. It creates no composable per node,
so use it for large trees or data that changes often. The nodes have no semantics and do not react
to input. Each node is drawn by a lambda, where `size` is the size of the node:
```kotlin
@Composable
fun MyCanvasChart(data: Tree<Double>) {
    val borderColor = MaterialTheme.colors.onBackground
    CanvasTreemapChart(
        data = data,
        evaluateItem = { it },
        modifier = Modifier.fillMaxSize(),
    ) { node ->
        drawRect(color = borderColor, style = Stroke(1.dp.toPx()))
    }
}
```

### Android Views
For Android Views, add the views module instead of the compose one:
```gradle
implementation "io.github.overpas:treemap-chart-views:0.2.0"
```
There are two ways to make a chart:
- `TreemapChartLayout` places the views you add to it, in XML or in code. Use it when the chart
  shape is fixed, for example written in XML.
- `TreemapChartView` builds the chart from a `Tree`. Use it when the data comes at runtime or
  changes: call `setData` again for each new data set.

Build the chart from a tree in code:
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

findViewById<TreemapChartView>(R.id.chart).setData(simpleTreeData, Int::toDouble) { parent, item ->
    TextView(parent.context).apply {
        text = item.toString()
        gravity = Gravity.CENTER
    }
}
```
Or write it in XML with nested `TreemapChartLayout`s, where each child sets its weight:
```xml
<by.overpass.treemapchart.views.TreemapChartLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="6"
        app:layout_treemapWeight="6" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="4"
        app:layout_treemapWeight="4" />

</by.overpass.treemapchart.views.TreemapChartLayout>
```
Set `measurer` on `TreemapChartView` or `TreemapChartLayout` to change the layout algorithm.

## Sample

For more advanced Kotlin Multiplatform samples check out the [sample](https://github.com/overpas/compose-treemap-chart/tree/master/sample) directory

## Development
Set the git hooks path to `config/git-hooks` to run detekt before each commit:
```shell
git config --local core.hooksPath config/git-hooks
```
