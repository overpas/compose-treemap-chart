package by.overpass.treemapchart.core.measure.squarified

import androidx.compose.runtime.Stable

/**
 * An intermediary object to represent a [by.overpass.treemapchart.core.measure.TreemapNode]
 */
@Stable
internal data class TreemapElement(
    var area: Double,
    var left: Double = 0.0,
    var top: Double = 0.0,
    var width: Double = 0.0,
    var height: Double = 0.0,
)
