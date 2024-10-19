package by.overpass.treemapchart.core.measure.squarified

import androidx.compose.runtime.Stable
import by.overpass.treemapchart.core.measure.LayoutOrientation
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * An implementation of [TreemapChartMeasurer] that keeps the aspect ratio of the nodes
 * as close to 1 as possible
 * Uses the squarified treemap algorithm: http://www.win.tue.nl/~vanwijk/stm.pdf
 * Implementation inspired by https://github.com/tasubo/javafx-chart-treemap
 */
@Stable
class SquarifiedMeasurer2 : TreemapChartMeasurer {

    private var height = 0.0
    private var width = 0.0
    private var heightLeft = 0.0
    private var widthLeft = 0.0
    private var left = 0.0
    private var top = 0.0
    private var layoutOrientation = LayoutOrientation.VERTICAL

    override fun measureNodes(values: List<Double>, width: Int, height: Int): List<TreemapNode> {
        val children = mutableListOf<TreemapElement>()
        setupSizeAndValues(children, width.toDouble(), height.toDouble(), values)
        return measureNodes(children)
    }

    private fun setupSizeAndValues(
        children: MutableList<TreemapElement>,
        width: Double,
        height: Double,
        values: List<Double>,
    ) {
        this.width = width
        this.height = height
        this.left = 0.0
        this.top = 0.0
        children.clear()
        for (value in values) {
            val treemapElement = TreemapElement(value)
            children.add(treemapElement)
        }
        layoutOrientation = if (width > height) {
            LayoutOrientation.VERTICAL
        } else {
            LayoutOrientation.HORIZONTAL
        }
        scaleArea(children)
    }

    private fun measureNodes(children: List<TreemapElement>): List<TreemapNode> {
        val treemapNodeList: MutableList<TreemapNode> = ArrayList()
        heightLeft = height
        widthLeft = width
        squarify(ArrayList(children), ArrayList(), minimumSide())
        for (child in children) {
            val treemapNode = TreemapNode(
                child.width.toInt(),
                child.height.toInt(),
                child.left.toInt(),
                child.top.toInt()
            )
            treemapNodeList.add(treemapNode)
            check(child.top <= height) { "Top is bigger than height" }
            check(child.left <= width) { "Left is bigger than width" }
        }
        return treemapNodeList
    }

    private fun squarify(children: List<TreemapElement>, row: List<TreemapElement>, w: Double) {
        val remainPopped = ArrayDeque(children)
        val c = remainPopped.removeFirst()
        val concatRow = ArrayList(row)
        concatRow.add(c)
        val remaining = ArrayList(remainPopped)
        val worstConcat = worst(concatRow, w)
        val worstRow = worst(row, w)
        if (row.isEmpty() || worstRow > worstConcat || isDoubleEqual(worstRow, worstConcat)) {
            if (remaining.isEmpty()) {
                layoutRow(concatRow, w)
            } else {
                squarify(remaining, concatRow, w)
            }
        } else {
            layoutRow(row, w)
            squarify(children, ArrayList(), minimumSide())
        }
    }

    private fun worst(ch: List<TreemapElement>, w: Double): Double {
        if (ch.isEmpty()) {
            return Double.MAX_VALUE
        }
        var areaSum = 0.0
        var maxArea = 0.0
        var minArea = Double.MAX_VALUE
        for (item in ch) {
            val area = item.area
            areaSum += area
            minArea = min(minArea, area)
            maxArea = max(maxArea, area)
        }
        val sqw = w * w
        val sqAreaSum = areaSum * areaSum
        return max(
            sqw * maxArea / sqAreaSum,
            sqAreaSum / (sqw * minArea)
        )
    }

    private fun layoutRow(row: List<TreemapElement>, w: Double) {
        var totalArea = 0.0
        for (element in row) {
            val area = element.area
            totalArea += area
        }
        if (layoutOrientation == LayoutOrientation.VERTICAL) {
            val rowWidth = totalArea / w
            var topItem = 0.0
            for (element in row) {
                val area = element.area
                val h = area / rowWidth
                element.top = top + topItem
                element.left = left
                element.width = rowWidth
                element.height = h
                topItem += h
            }
            widthLeft -= rowWidth
            //this.heightLeft -= w;
            left += rowWidth
            val minimumSide = minimumSide()
            if (!isDoubleEqual(minimumSide, heightLeft)) {
                changeLayout()
            }
        } else {
            val rowHeight = totalArea / w
            var rowLeft = 0.0
            for (item in row) {
                val area = item.area
                val wi = area / rowHeight
                item.top = top
                item.left = left + rowLeft
                item.height = rowHeight
                item.width = wi
                rowLeft += wi
            }
            //this.widthLeft -= rowHeight;
            heightLeft -= rowHeight
            top += rowHeight
            val minimumSide = minimumSide()
            if (!isDoubleEqual(minimumSide, widthLeft)) {
                changeLayout()
            }
        }
    }

    private fun changeLayout() {
        layoutOrientation =
            if (layoutOrientation == LayoutOrientation.HORIZONTAL) {
                LayoutOrientation.VERTICAL
            } else {
                LayoutOrientation.HORIZONTAL
            }
    }

    private fun isDoubleEqual(one: Double, two: Double): Boolean {
        val eps = 0.00001
        return abs(one - two) < eps
    }

    private fun minimumSide(): Double {
        return min(heightLeft, widthLeft)
    }

    private fun scaleArea(children: List<TreemapElement>) {
        val areaGiven = width * height
        var areaTotalTaken = 0.0
        for (child in children) {
            val area = child.area
            areaTotalTaken += area
        }
        val ratio = areaTotalTaken / areaGiven
        for (child in children) {
            val area = child.area / ratio
            child.area = area
        }
    }
}
