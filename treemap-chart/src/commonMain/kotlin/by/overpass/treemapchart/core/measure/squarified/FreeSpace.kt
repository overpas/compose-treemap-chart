package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.LayoutOrientation
import kotlin.math.min

internal class FreeSpace private constructor(
    private val left: Double,
    private val top: Double,
    private val width: Double,
    private val height: Double,
    private val orientation: LayoutOrientation,
) {

    val shortSide: Double
        get() = min(height, width)

    constructor(
        width: Double,
        height: Double,
    ) : this(
        left = 0.0,
        top = 0.0,
        width = width,
        height = height,
        orientation = if (width > height) {
            LayoutOrientation.VERTICAL
        } else {
            LayoutOrientation.HORIZONTAL
        },
    )

    fun place(row: Row): FreeSpace {
        val thickness = row.area / shortSide
        return if (orientation == LayoutOrientation.VERTICAL) {
            row.elements.fold(0.0) { offset, element ->
                val nextOffset = offset + element.area / thickness
                element.top = top + offset
                element.left = left
                element.right = left + thickness
                element.bottom = top + nextOffset
                nextOffset
            }
            val remainingWidth = width - thickness
            FreeSpace(
                left = left + thickness,
                top = top,
                width = remainingWidth,
                height = height,
                orientation = if (min(height, remainingWidth).isCloseTo(height)) {
                    LayoutOrientation.VERTICAL
                } else {
                    LayoutOrientation.HORIZONTAL
                },
            )
        } else {
            row.elements.fold(0.0) { offset, element ->
                val nextOffset = offset + element.area / thickness
                element.top = top
                element.left = left + offset
                element.bottom = top + thickness
                element.right = left + nextOffset
                nextOffset
            }
            val remainingHeight = height - thickness
            FreeSpace(
                left = left,
                top = top + thickness,
                width = width,
                height = remainingHeight,
                orientation = if (min(remainingHeight, width).isCloseTo(width)) {
                    LayoutOrientation.HORIZONTAL
                } else {
                    LayoutOrientation.VERTICAL
                },
            )
        }
    }
}
