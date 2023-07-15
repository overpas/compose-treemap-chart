package by.overpass.treemapchart.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.core.tree.Tree

/**
 * Treemap chart UI
 *
 * @param data Items to be displayed
 * @param evaluateItem Function that evaluates an item
 * @param modifier Modifier to be applied to the layout
 * @param itemContent UI for a leaf treemap item; Must contain exactly 1 top-level Composable
 */
@Composable
fun <T> TreemapChart(
    data: Tree<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit,
) {
    Box(modifier) {
        TreemapChartNode(
            data = data.root,
            evaluateItem = evaluateItem,
            itemContent = itemContent,
        )
    }
}

/**
 * Treemap chart UI
 *
 * @param data Items to be displayed
 * @param evaluateItem Function that evaluates an item
 * @param modifier Modifier to be applied to the layout
 * @param nodeContent UI for a treemap node (leaf or group); Must contain exactly 1 top-level Composable
 */
@Composable
fun <T> TreemapChart(
    data: Tree<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    nodeContent: @Composable (
        data: Tree.Node<T>,
        groupContent: @Composable (Tree.Node<T>) -> Unit,
    ) -> Unit,
) {
    Box(modifier) {
        TreemapChartNode(
            data = data.root,
            evaluateItem = evaluateItem,
            nodeContent = nodeContent,
        )
    }
}

/**
 * Treemap chart node UI
 *
 * @param data Item to be displayed
 * @param evaluateItem Function that evaluates an item
 * @param itemContent UI for a leaf treemap item
 */
@Composable
fun <T> TreemapChartNode(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    itemContent: @Composable (T) -> Unit,
) {
    TreemapChartNode(
        data = data,
        evaluateItem = evaluateItem,
    ) { node, GroupContent ->
        if (node.children.isEmpty()) {
            itemContent(node.data)
        } else {
            GroupContent(node)
        }
    }
}

/**
 * Treemap chart node UI
 *
 * @param data Item to be displayed
 * @param evaluateItem Function that evaluates an item
 * @param nodeContent UI for a treemap node (leaf or group)
 */
@Composable
fun <T> TreemapChartNode(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    nodeContent: @Composable (
        data: Tree.Node<T>,
        groupContent: @Composable (Tree.Node<T>) -> Unit,
    ) -> Unit,
) {
    nodeContent(data) { node ->
        TreemapChartLayout(
            data = node,
            evaluateItem = evaluateItem,
        ) { childNode ->
            TreemapChartNode(
                data = childNode,
                evaluateItem = evaluateItem,
                nodeContent = nodeContent,
            )
        }
    }
}

/**
 * Treemap chart leaf node UI
 *
 * @param data Item to be displayed
 * @param evaluateItem Function that evaluates an item
 * @param modifier Modifier to be applied to the layout.
 * @param itemContent UI for a leaf treemap item
 */
@Composable
fun <T> TreemapChartLayout(
    data: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    itemContent: @Composable (Tree.Node<T>) -> Unit,
) {
    val treemapChartMeasurer = LocalTreemapChartMeasurer.current
    Layout(
        content = {
            data.children.forEach { node ->
                itemContent(node)
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

/**
 * Basic treemap item UI
 *
 * @param item The displayed text
 * @param modifier Modifier to be applied to the layout.
 */
@Composable
fun SimpleTreemapItem(item: String, modifier: Modifier = Modifier) {
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
