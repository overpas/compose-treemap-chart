package by.overpass.treemapchart.compose

import androidx.compose.ui.unit.IntSize
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.Tree

internal fun <T> TreemapChartMeasurer.measureTreemap(
    root: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    size: IntSize,
): List<CanvasTreemapNode<T>> {
    val nodes = ArrayList<CanvasTreemapNode<T>>()
    nodes.addMeasured(
        parent = CanvasTreemapNode(node = root, left = 0, top = 0, width = size.width, height = size.height),
        evaluateItem = evaluateItem,
        measurer = this,
    )
    return nodes
}

internal fun <T> List<CanvasTreemapNode<T>>.mirrored(width: Int): List<CanvasTreemapNode<T>> =
    map { it.copy(left = width - it.left - it.width) }

private fun <T> MutableList<CanvasTreemapNode<T>>.addMeasured(
    parent: CanvasTreemapNode<T>,
    evaluateItem: (T) -> Double,
    measurer: TreemapChartMeasurer,
) {
    add(parent)
    val children = parent.node.children
    if (children.isEmpty()) return
    val weights = children.map { child ->
        evaluateItem(child.data).also { weight ->
            require(weight > 0.0) { "Every node of CanvasTreemapChart must have a positive weight" }
        }
    }
    val measured = measurer.measureNodes(weights, parent.width, parent.height)
    children.forEachIndexed { index, child ->
        val bounds = measured[index]
        addMeasured(
            parent = CanvasTreemapNode(
                node = child,
                left = parent.left + bounds.offsetX,
                top = parent.top + bounds.offsetY,
                width = bounds.width,
                height = bounds.height,
            ),
            evaluateItem = evaluateItem,
            measurer = measurer,
        )
    }
}
