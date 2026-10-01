package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode

/**
 * An intermediary object to represent a [TreemapNode].
 */
internal class TreemapElement(
    var area: Double,
    var left: Double = 0.0,
    var top: Double = 0.0,
    var width: Double = 0.0,
    var height: Double = 0.0,
) {

    fun toNode(): TreemapNode =
        TreemapNode(
            width = width.toInt(),
            height = height.toInt(),
            offsetX = left.toInt(),
            offsetY = top.toInt(),
        )
}
