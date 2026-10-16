package by.overpass.treemapchart.core.measure.squarified

import kotlin.math.abs

private const val EPSILON = 0.00001

internal fun Double.isCloseTo(other: Double): Boolean =
    abs(this - other) < EPSILON
