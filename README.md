# Compose Treemap Chart
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.overpas/treemap-compose-android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.overpas/treemap-compose-android)
[![Test](https://github.com/overpas/compose-treemap-chart/actions/workflows/test.yml/badge.svg)](https://github.com/overpas/compose-treemap-chart/actions/workflows/test.yml)
[![Static analysis](https://github.com/overpas/compose-treemap-chart/actions/workflows/static-analysis.yml/badge.svg)](https://github.com/overpas/compose-treemap-chart/actions/workflows/static-analysis.yml)
[![codecov](https://codecov.io/gh/overpas/compose-treemap-chart/branch/master/graph/badge.svg?token=CHYdQiizEr)](https://codecov.io/gh/overpas/compose-treemap-chart)

Jetpack compose [treemap](https://en.wikipedia.org/wiki/Treemapping) chart implementation

## Usage
### Adding to the project
Add this to your `dependencies` gradle block:
```gradle
implementation "io.github.overpas:treemap-core:0.0.3"
implementation "io.github.overpas:treemap-compose-android:0.0.3"
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetreemapchartTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TreemapChart(
                        data = simpleTreeData,
                        evaluateItem = { it.toDouble() },
                        treemapChartMeasurer = remember { SquarifiedMeasurer() },
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                    ) { item ->
                        TreemapItem(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun TreemapItem(item: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.border(1.dp, MaterialTheme.colors.onBackground),
    ) {
        Text(
            text = item.toString(),
            textAlign = TextAlign.Center,
        )
    }
}
```
The code above produces something like this:
<img src="https://raw.githubusercontent.com/overpas/compose-treemap-chart/master/img/sample_treemap.png" width="750">
