package by.overpass.treemapchart.compose

import androidx.compose.runtime.compositionLocalOf
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer

val LocalTreemapChartMeasurer = compositionLocalOf<TreemapChartMeasurer> { SquarifiedMeasurer() }
