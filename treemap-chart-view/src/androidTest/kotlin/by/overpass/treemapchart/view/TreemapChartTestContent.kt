package by.overpass.treemapchart.view

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.test.ext.junit.rules.ActivityScenarioRule
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.tree

internal val sampleTreeData = tree(10) {
    node(6) {
        node(4)
        node(2) {
            node(1)
            node(1)
        }
    }
    node(3) {
        node(2)
        node(1)
    }
    node(1)
}

internal fun ActivityScenarioRule<TreemapChartTestActivity>.setTreemapChartContent(measurer: TreemapChartMeasurer) {
    scenario.onActivity { activity ->
        activity.chart.measurer = measurer
        activity.chart.setData(sampleTreeData, Int::toDouble) { parent, item ->
            SimpleTreemapItemView(parent.context).apply { text = item.toString() }
        }
    }
}

internal fun ActivityScenarioRule<TreemapChartTestActivity>.leafTexts(): List<String> {
    val texts = mutableListOf<String>()
    scenario.onActivity { activity ->
        activity.chart.leafViews().mapTo(texts) { it.text.toString() }
    }
    return texts.sorted()
}

internal fun ActivityScenarioRule<TreemapChartTestActivity>.leafBounds(): List<Rect> {
    val bounds = mutableListOf<Rect>()
    scenario.onActivity { activity ->
        activity.chart.leafViews().mapTo(bounds) { it.boundsIn(activity.chart) }
    }
    return bounds
}

internal fun View.leafViews(): List<SimpleTreemapItemView> =
    when (this) {
        is SimpleTreemapItemView -> listOf(this)
        is ViewGroup -> (0..<childCount).flatMap { getChildAt(it).leafViews() }
        else -> emptyList()
    }

internal fun View.boundsIn(ancestor: View): Rect {
    val location = IntArray(2)
    val ancestorLocation = IntArray(2)
    getLocationInWindow(location)
    ancestor.getLocationInWindow(ancestorLocation)
    val left = location[0] - ancestorLocation[0]
    val top = location[1] - ancestorLocation[1]
    return Rect(left, top, left + width, top + height)
}
