package by.overpass.treemapchart.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import by.overpass.treemapchart.core.tree.Tree

/**
 * Treemap chart that draws all nodes of [data] on one canvas. It creates no composable per node, so
 * it is faster than [TreemapChart] for large trees. The nodes are not composables, so they have no
 * semantics and do not react to input.
 *
 * The measurer comes from [LocalTreemapChartMeasurer]. The chart lays the nodes out again only when
 * its size, layout direction or inputs change.
 *
 * When other content animates while the chart stays the same, apply
 * `Modifier.graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)` to the chart. Then the
 * frames of that animation draw a cached texture instead of every node. The texture takes memory in
 * proportion to the size of the chart.
 *
 * @param T Type of the node values
 * @param data The tree to draw. The root takes the full size of the chart
 * @param evaluateItem The weight of a node, must be finite and positive. It is not called for the root
 * @param modifier Modifier to be applied to the chart
 * @param drawNode Draws one node. Every node is drawn, parents before their children. In the
 * lambda, `size` is the size of the node and the origin is its top-left corner
 */
@Composable
fun <T> CanvasTreemapChart(
    data: Tree<T>,
    evaluateItem: (T) -> Double,
    modifier: Modifier = Modifier,
    drawNode: DrawScope.(node: Tree.Node<T>) -> Unit,
) {
    val measurer = LocalTreemapChartMeasurer.current
    Spacer(
        modifier.drawWithCache {
            val chartSize = IntSize(size.width.toInt(), size.height.toInt())
            val measured = measurer.measureTreemap(
                root = data.root,
                evaluateItem = evaluateItem,
                size = chartSize,
            )
            val nodes = when (layoutDirection) {
                LayoutDirection.Ltr -> measured
                LayoutDirection.Rtl -> measured.mirrored(chartSize.width)
            }
            onDrawBehind {
                nodes.forEach { node ->
                    inset(
                        left = node.left.toFloat(),
                        top = node.top.toFloat(),
                        right = size.width - node.left - node.width,
                        bottom = size.height - node.top - node.height,
                    ) {
                        drawNode(node.node)
                    }
                }
            }
        },
    )
}
