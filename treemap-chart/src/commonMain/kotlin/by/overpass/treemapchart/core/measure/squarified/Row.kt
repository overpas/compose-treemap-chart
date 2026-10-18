package by.overpass.treemapchart.core.measure.squarified

private val EMPTY_SHAPE = RowShape(
    area = 0.0,
    maxArea = Double.NEGATIVE_INFINITY,
    minArea = Double.POSITIVE_INFINITY,
)

internal class Row private constructor(
    private val allElements: List<TreemapElement>,
    private val start: Int,
    private val end: Int,
    private val shape: RowShape,
) {

    val elements: List<TreemapElement>
        get() = allElements.subList(start, end)

    val area: Double
        get() = shape.area

    constructor(allElements: List<TreemapElement>) : this(
        allElements = allElements,
        start = 0,
        end = 0,
        shape = EMPTY_SHAPE,
    )

    fun acceptsNext(side: Double): Boolean {
        if (start == end) {
            return true
        }
        val rowWorst = shape.worst(side)
        val extendedWorst = (shape + allElements[end].area).worst(side)
        return rowWorst > extendedWorst || rowWorst.isCloseTo(extendedWorst)
    }

    fun extended(): Row =
        Row(
            allElements = allElements,
            start = start,
            end = end + 1,
            shape = shape + allElements[end].area,
        )

    fun following(): Row =
        Row(
            allElements = allElements,
            start = end,
            end = end,
            shape = EMPTY_SHAPE,
        )
}
