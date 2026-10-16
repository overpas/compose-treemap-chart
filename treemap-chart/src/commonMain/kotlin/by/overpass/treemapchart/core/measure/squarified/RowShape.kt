package by.overpass.treemapchart.core.measure.squarified

import kotlin.math.max
import kotlin.math.min

internal class RowShape(
    val area: Double,
    private val maxArea: Double,
    private val minArea: Double,
) {

    operator fun plus(elementArea: Double): RowShape =
        RowShape(
            area = area + elementArea,
            maxArea = max(maxArea, elementArea),
            minArea = min(minArea, elementArea),
        )

    fun worst(side: Double): Double {
        val squaredSide = side * side
        val squaredArea = area * area
        return max(
            squaredSide * maxArea / squaredArea,
            squaredArea / (squaredSide * minArea),
        )
    }
}
