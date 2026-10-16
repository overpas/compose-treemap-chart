package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode

/**
 * An implementation of [TreemapChartMeasurer] that keeps the aspect ratio of the nodes
 * as close to 1 as possible
 * Uses the squarified treemap algorithm: http://www.win.tue.nl/~vanwijk/stm.pdf
 * Implementation inspired by https://github.com/tasubo/javafx-chart-treemap
 */
class SquarifiedMeasurer : TreemapChartMeasurer {

    override fun measureNodes(
        values: List<Double>,
        width: Int,
        height: Int,
    ): List<TreemapNode> =
        Squarification(values, width.toDouble(), height.toDouble()).nodes()
}
