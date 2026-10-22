package by.overpass.treemapchart.core.measure.sliceanddice

import by.overpass.treemapchart.core.measure.LayoutOrientation
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode
import kotlin.math.roundToInt

/**
 * An implementation of [TreemapChartMeasurer] that positions treemap nodes the "slice-and-dice" way.
 */
data object SliceAndDiceMeasurer : TreemapChartMeasurer {

    override fun measureNodes(
        values: List<Double>,
        width: Int,
        height: Int,
    ): List<TreemapNode> {
        val orientation =
            if (height > width) LayoutOrientation.VERTICAL else LayoutOrientation.HORIZONTAL
        val dividedDimension = if (orientation == LayoutOrientation.VERTICAL) height else width
        val totalAmount = values.sum()
        return values
            .runningFold(0.0) { position, value -> position + dividedDimension / totalAmount * value }
            .zipWithNext { start, end ->
                val offset = start.roundToInt()
                val size = end.roundToInt() - offset
                if (orientation == LayoutOrientation.VERTICAL) {
                    TreemapNode(
                        width = width,
                        height = size,
                        offsetX = 0,
                        offsetY = offset,
                    )
                } else {
                    TreemapNode(
                        width = size,
                        height = height,
                        offsetX = offset,
                        offsetY = 0,
                    )
                }
            }
    }
}
