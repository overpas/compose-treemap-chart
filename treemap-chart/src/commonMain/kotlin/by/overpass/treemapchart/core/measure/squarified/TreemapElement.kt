package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode
import kotlin.math.roundToInt

/**
 * An intermediary object to represent a [TreemapNode].
 */
internal class TreemapElement(
    var area: Double,
    var left: Double = 0.0,
    var top: Double = 0.0,
    var right: Double = 0.0,
    var bottom: Double = 0.0,
) {

    fun toNode(): TreemapNode {
        val offsetX = left.roundToInt()
        val offsetY = top.roundToInt()
        return TreemapNode(
            width = right.roundToInt() - offsetX,
            height = bottom.roundToInt() - offsetY,
            offsetX = offsetX,
            offsetY = offsetY,
        )
    }
}
