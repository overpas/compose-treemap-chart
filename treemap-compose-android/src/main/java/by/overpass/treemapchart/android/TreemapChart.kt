@file:Suppress("FunctionNaming", "FunctionParameterNaming", "MagicNumber")

package by.overpass.treemapchart.android

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.Tree
import by.overpass.treemapchart.core.tree.tree

internal val sampleTreeData = tree(10) {
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
fun SimpleTreemapItem(item: Int, modifier: Modifier = Modifier) {
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

@Composable
fun <T> TreemapChart(
    data: Tree<T>,
    evaluateItem: (T) -> Double,
    treemapChartMeasurer: TreemapChartMeasurer,
    modifier: Modifier = Modifier,
    ItemContent: @Composable (T) -> Unit,
) {
    Box(modifier) {
        TreemapChartNode(
            data = data.root,
            evaluateItem = evaluateItem,
            treemapChartMeasurer = treemapChartMeasurer,
            ItemContent = ItemContent,
        )
    }
}

@Composable
fun <T> TreemapChartNode(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    treemapChartMeasurer: TreemapChartMeasurer,
    ItemContent: @Composable (T) -> Unit,
) {
    if (data.children.isEmpty()) {
        ItemContent(data.data)
    } else {
        TreemapChart(
            data = data,
            evaluateItem = evaluateItem,
            treemapChartMeasurer = treemapChartMeasurer,
        ) { node ->
            TreemapChartNode(
                data = node,
                evaluateItem = evaluateItem,
                treemapChartMeasurer = treemapChartMeasurer,
                ItemContent = ItemContent,
            )
        }
    }
}

@Composable
fun <T> TreemapChart(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    treemapChartMeasurer: TreemapChartMeasurer,
    ItemContent: @Composable (Tree.Node<T>) -> Unit,
) {
    Layout(
        content = {
            data.children.forEach { node ->
                ItemContent(node)
            }
        },
    ) { measurables, constraints ->
        val nodes = treemapChartMeasurer.measureNodes(
            data.children.map { evaluateItem(it.data) },
            constraints.maxWidth,
            constraints.maxHeight,
        )
        val placeables = measurables.mapIndexed { index, measurable ->
            measurable.measure(Constraints.fixed(nodes[index].width, nodes[index].height))
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(nodes[index].offsetX, nodes[index].offsetY)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTreemapChart() {
    MaterialTheme {
        TreemapChart(
            data = sampleTreeData,
            evaluateItem = { it.toDouble() },
            treemapChartMeasurer = remember { SquarifiedMeasurer() }
        ) {
            SimpleTreemapItem(it)
        }
    }
}