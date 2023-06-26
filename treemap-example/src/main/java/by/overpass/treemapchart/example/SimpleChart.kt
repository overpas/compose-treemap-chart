package by.overpass.treemapchart.example

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            EasyChartItem(item = item)
        }
        Button(onClick = onGoToComplexChartClick) {
            Text(text = "Show more complex chart")
        }
    }
}

@Composable
private fun EasyChartItem(item: Int, modifier: Modifier = Modifier) {
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
