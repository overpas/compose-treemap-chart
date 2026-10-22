package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode

internal class Squarification(
    values: List<Double>,
    private val width: Double,
    private val height: Double,
) {

    private val elements = run {
        val ratio = values.sum() / (width * height)
        values.map { TreemapElement(it / ratio) }
    }

    fun nodes(): List<TreemapNode> {
        squarify()
        return elements.map { element ->
            check(element.top <= height || element.top.isCloseTo(height)) { "Top is bigger than height" }
            check(element.left <= width || element.left.isCloseTo(width)) { "Left is bigger than width" }
            element.toNode()
        }
    }

    private fun squarify() {
        val (lastRow, freeSpace) = elements.fold(Row(elements) to FreeSpace(width, height)) { (row, space), _ ->
            if (row.acceptsNext(space.shortSide)) {
                row.extended() to space
            } else {
                row.following().extended() to space.place(row)
            }
        }
        freeSpace.place(lastRow)
    }
}
