package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.LayoutOrientation
import kotlin.math.min

internal class FreeSpace(
    private var width: Double,
    private var height: Double,
) {

    private var left = 0.0
    private var top = 0.0
    private var orientation = if (width > height) {
        LayoutOrientation.VERTICAL
    } else {
        LayoutOrientation.HORIZONTAL
    }

    val shortSide: Double
        get() = min(height, width)

    fun place(
        row: Row,
        side: Double,
    ) {
        val thickness = row.area / side
        var offset = 0.0
        if (orientation == LayoutOrientation.VERTICAL) {
            row.elements.forEach { element ->
                element.top = top + offset
                element.left = left
                element.right = left + thickness
                offset += element.area / thickness
                element.bottom = top + offset
            }
            width -= thickness
            left += thickness
            if (!shortSide.isCloseTo(height)) {
                orientation = LayoutOrientation.HORIZONTAL
            }
        } else {
            row.elements.forEach { element ->
                element.top = top
                element.left = left + offset
                element.bottom = top + thickness
                offset += element.area / thickness
                element.right = left + offset
            }
            height -= thickness
            top += thickness
            if (!shortSide.isCloseTo(width)) {
                orientation = LayoutOrientation.VERTICAL
            }
        }
    }
}
