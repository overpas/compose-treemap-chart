@file:JvmName("TreemapChartKt")
@file:JvmMultifileClass

package by.overpass.treemapchart.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.core.tree.Tree
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Treemap chart UI.
 *
 * @param T type of the node values
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
 * Treemap chart UI.
 *
 * @param T type of the node values
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
 * Treemap chart node UI.
 *
 * @param T type of the node values
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
    ) { node, groupContent ->
        if (node.children.isEmpty()) {
            itemContent(node.data)
        } else {
            groupContent(node)
        }
    }
}

/**
 * Treemap chart node UI.
 *
 * @param T type of the node values
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
