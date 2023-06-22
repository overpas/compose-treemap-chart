package by.overpass.treemapchart.android

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
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

/**
 * Basic UI to represent a treemap item
 *
 * @param item item to be displayed
 * @param modifier compose modifier
 */
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

/**
 * Treemap chart UI
 *
 * @param data items to be displayed
 * @param evaluateItem function that evaluates an item
 * @param modifier compose modifier
 * @param ItemContent UI for a leaf treemap item
 */
@Composable
fun <T> TreemapChart(
    data: Tree<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    ItemContent: @Composable (T) -> Unit,
) {
    LocalContext
    Box(modifier) {
        TreemapChartNode(
            data = data.root,
            evaluateItem = evaluateItem,
            ItemContent = ItemContent,
        )
    }
}

/**
 * Treemap chart UI
 *
 * @param data items to be displayed
 * @param evaluateItem function that evaluates an item
 * @param modifier compose modifier
 * @param NodeContent UI for a treemap node (leaf or group)
 */
@Composable
fun <T> TreemapChart(
    data: Tree<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    NodeContent: @Composable (
        data: Tree.Node<T>,
        groupContent: @Composable (Tree.Node<T>) -> Unit,
    ) -> Unit,
) {
    Box(modifier) {
        TreemapChartNode(
            data = data.root,
            evaluateItem = evaluateItem,
            NodeContent = NodeContent,
        )
    }
}

/**
 * Treemap chart node UI
 *
 * @param data item to be displayed
 * @param evaluateItem function that evaluates an item
 * @param ItemContent UI for a leaf treemap item
 */
@Composable
fun <T> TreemapChartNode(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    ItemContent: @Composable (T) -> Unit,
) {
    TreemapChartNode(
        data = data,
        evaluateItem = evaluateItem,
    ) { node, GroupContent ->
        if (node.children.isEmpty()) {
            ItemContent(node.data)
        } else {
            GroupContent(node)
        }
    }
}

/**
 * Treemap chart node UI
 *
 * @param data item to be displayed
 * @param evaluateItem function that evaluates an item
 * @param NodeContent UI for a treemap node (leaf or group)
 */
@Composable
fun <T> TreemapChartNode(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    NodeContent: @Composable (
        data: Tree.Node<T>,
        groupContent: @Composable (Tree.Node<T>) -> Unit,
    ) -> Unit,
) {
    NodeContent(
        data = data,
        groupContent = { node ->
            TreemapChartLayout(
                data = node,
                evaluateItem = evaluateItem,
            ) { childNode ->
                TreemapChartNode(
                    data = childNode,
                    evaluateItem = evaluateItem,
                    NodeContent = NodeContent,
                )
            }
        },
    )
}

/**
 * Treemap chart leaf node UI
 *
 * @param data item to be displayed
 * @param evaluateItem function that evaluates an item
 * @param ItemContent UI for a leaf treemap item
 */
@Composable
fun <T> TreemapChartLayout(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    ItemContent: @Composable (Tree.Node<T>) -> Unit,
) {
    val treemapChartMeasurer = LocalTreemapChartMeasurer.current
    Layout(
        content = {
            data.children.forEach { node ->
                ItemContent(node)
            }
        },
        modifier = modifier,
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

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewTreemapChart() {
    MaterialTheme {
        TreemapChart(
            data = sampleTreeData,
            evaluateItem = Int::toDouble,
        ) { data ->
            SimpleTreemapItem(data)
        }
    }
}
