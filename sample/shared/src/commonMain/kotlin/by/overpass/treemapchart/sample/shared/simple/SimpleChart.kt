package by.overpass.treemapchart.sample.shared.simple

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.compose.TreemapChart

@Composable
internal fun SimpleChart(
    onGoToComplexChartClick: () -> Unit,
    onGoToLargeChartClick: () -> Unit,
    onGoToCanvasLargeChartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        SimpleTreemapChart(Modifier.weight(1f))
        Button(onClick = onGoToComplexChartClick) {
            Text(text = "Show more complex chart")
        }
        Button(onClick = onGoToLargeChartClick) {
            Text(text = "Show large chart")
        }
        Button(onClick = onGoToCanvasLargeChartClick) {
            Text(text = "Show large canvas chart")
        }
    }
}

@Composable
private fun SimpleTreemapChart(modifier: Modifier = Modifier) {
    TreemapChart(modifier) {
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
