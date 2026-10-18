package by.overpass.treemapchart.compose

import androidx.compose.ui.unit.IntSize
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.Tree

private class CanvasTreemapMeasurement<T>(
    private val measurer: TreemapChartMeasurer,
    private val evaluateItem: (T) -> Double,
) {

    val nodes = ArrayList<CanvasTreemapNode<T>>()

    fun CanvasTreemapNode<T>.addMeasured() {
        nodes += this
        val children = node.children
        if (children.isEmpty()) return
        val weights = children.map { child ->
            evaluateItem(child.data).also { weight ->
                require(weight > 0.0) { "Every node of CanvasTreemapChart must have a positive weight" }
            }
        }
        val measured = measurer.measureNodes(weights, width, height)
        children.forEachIndexed { index, child ->
            val bounds = measured[index]
            CanvasTreemapNode(
                node = child,
                left = left + bounds.offsetX,
                top = top + bounds.offsetY,
                width = bounds.width,
                height = bounds.height,
            ).addMeasured()
        }
    }
}

internal fun <T> TreemapChartMeasurer.measureTreemap(
    root: Tree.Node<T>,
    evaluateItem: (T) -> Double,
    size: IntSize,
): List<CanvasTreemapNode<T>> =
    with(CanvasTreemapMeasurement(measurer = this, evaluateItem = evaluateItem)) {
        CanvasTreemapNode(node = root, left = 0, top = 0, width = size.width, height = size.height).addMeasured()
        nodes
    }

internal fun <T> List<CanvasTreemapNode<T>>.mirrored(width: Int): List<CanvasTreemapNode<T>> =
    map { it.copy(left = width - it.left - it.width) }
