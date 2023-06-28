package by.overpass.treemapchart.sample.shared.simple

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.compose.SimpleTreemapItem
import by.overpass.treemapchart.compose.TreemapChart
import by.overpass.treemapchart.core.tree.tree

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
internal fun SimpleChart(
    modifier: Modifier = Modifier,
    onGoToComplexChartClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        TreemapChart(
            data = simpleTreeData,
            evaluateItem = Int::toDouble,
            modifier = Modifier
                .weight(1f),
        ) { item ->
            SimpleTreemapItem(item = item)
        }
        Button(onClick = onGoToComplexChartClick) {
            Text(text = "Show more complex chart")
        }
    }
}
