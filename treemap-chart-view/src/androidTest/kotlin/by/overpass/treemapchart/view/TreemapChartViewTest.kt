package by.overpass.treemapchart.view

import android.graphics.Rect
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class TreemapChartViewTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(TreemapChartTestActivity::class.java)

    @Test
    fun testSliceAndDiceTreemapDisplayed() {
        activityRule.setTreemapChartContent(SliceAndDiceMeasurer)

        onView(withText("4")).check(matches(isDisplayed()))
        onView(withText("2")).check(matches(isDisplayed()))
        assertEquals(listOf("1", "1", "1", "1", "2", "4"), activityRule.leafTexts())
    }

    @Test
    fun testSquarifiedTreemapDisplayed() {
        activityRule.setTreemapChartContent(SquarifiedMeasurer())

        onView(withText("4")).check(matches(isDisplayed()))
        onView(withText("2")).check(matches(isDisplayed()))
        assertEquals(listOf("1", "1", "1", "1", "2", "4"), activityRule.leafTexts())
    }

    @Test
    fun testTopLevelNodesMatchMeasurer() {
        activityRule.setTreemapChartContent(SliceAndDiceMeasurer)
        onView(withText("4")).check(matches(isDisplayed()))

        val expected = mutableListOf<Rect>()
        val actual = mutableListOf<Rect>()
        activityRule.scenario.onActivity { activity ->
            val chart = activity.chart
            val group = chart.getChildAt(0) as TreemapChartLayout
            SliceAndDiceMeasurer
                .measureNodes(listOf(6.0, 3.0, 1.0), chart.width, chart.height)
                .mapTo(expected) { Rect(it.offsetX, it.offsetY, it.offsetX + it.width, it.offsetY + it.height) }
            (0..<group.childCount).mapTo(actual) { group.getChildAt(it).boundsIn(chart) }
        }

        assertEquals(expected, actual)
    }

    @Test
    fun testLeavesFillChart() {
        activityRule.setTreemapChartContent(SquarifiedMeasurer())
        onView(withText("4")).check(matches(isDisplayed()))

        var chartArea = 0
        activityRule.scenario.onActivity { activity ->
            chartArea = activity.chart.width * activity.chart.height
        }
        val leavesArea = activityRule.leafBounds().sumOf { it.width() * it.height() }

        assertEquals(chartArea.toDouble(), leavesArea.toDouble(), chartArea * 0.01)
    }

    @Test
    fun testMeasurerChangeRebuildsChart() {
        activityRule.setTreemapChartContent(SquarifiedMeasurer())
        onView(withText("4")).check(matches(isDisplayed()))
        val squarifiedBounds = activityRule.leafBounds()

        activityRule.scenario.onActivity { it.chart.measurer = SliceAndDiceMeasurer }
        onView(withText("4")).check(matches(isDisplayed()))

        assertEquals(listOf("1", "1", "1", "1", "2", "4"), activityRule.leafTexts())
        assertNotEquals(squarifiedBounds, activityRule.leafBounds())
    }

    @Test
    fun testRtlMirrorsLayout() {
        activityRule.setTreemapChartContent(SliceAndDiceMeasurer)
        onView(withText("4")).check(matches(isDisplayed()))
        val ltrBounds = activityRule.leafBounds()

        var chartWidth = 0
        activityRule.scenario.onActivity { activity ->
            chartWidth = activity.chart.width
            activity.chart.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
        activityRule.setTreemapChartContent(SliceAndDiceMeasurer)
        onView(withText("4")).check(matches(isDisplayed()))
        val mirrored = ltrBounds.map { Rect(chartWidth - it.right, it.top, chartWidth - it.left, it.bottom) }

        assertEquals(mirrored, activityRule.leafBounds())
    }
}
