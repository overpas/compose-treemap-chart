package by.overpass.treemapchart.core.measure.squarified

internal class Row {

    private var shape = RowShape(
        area = 0.0,
        maxArea = Double.NEGATIVE_INFINITY,
        minArea = Double.POSITIVE_INFINITY,
    )

    val elements = mutableListOf<TreemapElement>()

    val area: Double
        get() = shape.area

    fun add(element: TreemapElement) {
        elements += element
        shape += element.area
    }

    fun accepts(
        element: TreemapElement,
        side: Double,
    ): Boolean {
        if (elements.isEmpty()) {
            return true
        }
        val rowWorst = shape.worst(side)
        val extendedWorst = (shape + element.area).worst(side)
        return rowWorst > extendedWorst || rowWorst.isCloseTo(extendedWorst)
    }
}
