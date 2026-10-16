package by.overpass.treemapchart.core.measure.squarified

import kotlin.math.max

internal class Row(val elements: List<TreemapElement>) {

    val area: Double
        get() = elements.sumOf { it.area }

    fun isEmpty(): Boolean =
        elements.isEmpty()

    operator fun plus(element: TreemapElement): Row =
        Row(elements + element)

    fun worst(side: Double): Double {
        if (elements.isEmpty()) {
            return Double.MAX_VALUE
        }
        val squaredSide = side * side
        val squaredArea = area * area
        return max(
            squaredSide * elements.maxOf { it.area } / squaredArea,
            squaredArea / (squaredSide * elements.minOf { it.area }),
        )
    }
}
