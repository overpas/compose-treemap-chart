package by.overpass.treemapchart.sample.android

import android.graphics.Rect
import android.view.View
import by.overpass.treemapchart.view.TreemapChartView

internal data class ComparisonScreen(
    val root: View,
    val viewChart: TreemapChartView,
    val composeBounds: MutableMap<Int, Rect>,
)
