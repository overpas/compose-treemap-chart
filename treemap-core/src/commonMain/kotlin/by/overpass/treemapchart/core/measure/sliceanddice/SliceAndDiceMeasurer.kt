package by.overpass.treemapchart.core.measure.sliceanddice

import by.overpass.treemapchart.core.measure.LayoutOrientation
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode
import kotlin.math.roundToInt

/**
 * An implementation of [TreemapChartMeasurer] that positions treemap nodes the "slice-and-dice" way
 */
class SliceAndDiceMeasurer : TreemapChartMeasurer {

    override fun measureNodes(values: List<Double>, width: Int, height: Int): List<TreemapNode> {
        val orientation =
            if (height > width) LayoutOrientation.VERTICAL else LayoutOrientation.HORIZONTAL
        val dividedDimension = if (orientation == LayoutOrientation.VERTICAL) height else width
        val totalAmount = values.sum()
        var yPosition = 0.0
        var xPosition = 0.0
        return values.map { value ->
            val dimension = dividedDimension / totalAmount * value
            if (orientation == LayoutOrientation.VERTICAL) { // vertical
                val node = TreemapNode(
                    width = width,
                    height = dimension.roundToInt(),
                    offsetX = 0,
                    offsetY = yPosition.roundToInt(),
                )
                yPosition += dimension
                node
            } else { // horizontal
                val node = TreemapNode(
                    width = dimension.roundToInt(),
                    height = height,
                    offsetX = xPosition.roundToInt(),
                    offsetY = 0
                )
                xPosition += dimension
                node
            }
        }
    }
}
