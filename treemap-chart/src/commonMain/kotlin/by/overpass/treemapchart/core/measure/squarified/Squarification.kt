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
        squarify(elements, Row(emptyList()), freeSpace.shortSide)
        return elements.map { element ->
            check(element.top <= height) { "Top is bigger than height" }
            check(element.left <= width) { "Left is bigger than width" }
            element.toNode()
        }
    }

    private fun squarify(
        remaining: List<TreemapElement>,
        row: Row,
        side: Double,
    ) {
        val extendedRow = row + remaining.first()
        val rest = remaining.drop(1)
        val extendedWorst = extendedRow.worst(side)
        val rowWorst = row.worst(side)
        if (row.isEmpty() || rowWorst > extendedWorst || rowWorst.isCloseTo(extendedWorst)) {
            if (rest.isEmpty()) {
                freeSpace.place(extendedRow, side)
            } else {
                squarify(rest, extendedRow, side)
            }
        } else {
            freeSpace.place(row, side)
            squarify(remaining, Row(emptyList()), freeSpace.shortSide)
        }
    }
}
