package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode

internal class Squarification(
    values: List<Double>,
    private val width: Double,
    private val height: Double,
) {

    private val freeSpace = FreeSpace(width, height)
    private val elements = run {
        val ratio = values.sum() / (width * height)
        values.map { TreemapElement(it / ratio) }
    }

    fun nodes(): List<TreemapNode> {
        squarify()
        return elements.map { element ->
            check(element.top <= height) { "Top is bigger than height" }
            check(element.left <= width) { "Left is bigger than width" }
            element.toNode()
        }
    }

    private fun squarify() {
        var row = Row()
        var side = freeSpace.shortSide
        elements.forEach { element ->
            if (!row.accepts(element, side)) {
                freeSpace.place(row, side)
                row = Row()
                side = freeSpace.shortSide
            }
            row.add(element)
        }
        freeSpace.place(row, side)
    }
}
