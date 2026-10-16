@file:JvmName("TreemapChartKt")
@file:JvmMultifileClass

package by.overpass.treemapchart.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import by.overpass.treemapchart.core.tree.Tree
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Treemap chart leaf node UI.
 *
 * @param T type of the node values
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
