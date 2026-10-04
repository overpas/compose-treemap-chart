package by.overpass.treemapchart.view

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import by.overpass.treemapchart.core.measure.TreemapNode
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.tree
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TreemapChartViewTest {

    @Test
    fun testLeavesAreDisplayedWithSquarifiedMeasurer() {
        val data = tree("root") {
            node("A") {
                node("A1")
                node("A2")
            }
            node("B")
        }
        val values = mapOf("root" to 4.0, "A" to 3.0, "A1" to 2.0, "A2" to 1.0, "B" to 1.0)
        val scenario = ActivityScenario.launch(TreemapChartTestActivity::class.java)

        scenario.onActivity { activity ->
            val chart = TreemapChartView(activity)
            chart.measurer = SquarifiedMeasurer()
            chart.setData(data, { values.getValue(it) }) { parent, item ->
                SimpleTreemapItemView(parent.context).apply { text = item }
            }
            activity.setContentView(chart)
        }

        onView(withText("A1")).check(matches(isDisplayed()))
        onView(withText("A2")).check(matches(isDisplayed()))
        onView(withText("B")).check(matches(isDisplayed()))
        scenario.close()
    }

    @Test
    fun testLeavesAreDisplayedWithSliceAndDiceMeasurer() {
        val data = tree("root") {
            node("A") {
                node("A1")
                node("A2")
            }
            node("B")
        }
        val values = mapOf("root" to 4.0, "A" to 3.0, "A1" to 2.0, "A2" to 1.0, "B" to 1.0)
        val scenario = ActivityScenario.launch(TreemapChartTestActivity::class.java)

        scenario.onActivity { activity ->
            val chart = TreemapChartView(activity)
            chart.measurer = SliceAndDiceMeasurer
            chart.setData(data, { values.getValue(it) }) { parent, item ->
                SimpleTreemapItemView(parent.context).apply { text = item }
            }
            activity.setContentView(chart)
        }

        onView(withText("A1")).check(matches(isDisplayed()))
        onView(withText("A2")).check(matches(isDisplayed()))
        onView(withText("B")).check(matches(isDisplayed()))
        scenario.close()
    }

    @Test
    fun testTopLevelNodeBoundsMatchMeasurerOutput() {
        val data = tree(10) {
            node(6)
            node(3)
            node(1)
        }
        val scenario = ActivityScenario.launch(TreemapChartTestActivity::class.java)
        lateinit var chart: TreemapChartView
        scenario.onActivity { activity ->
            chart = TreemapChartView(activity)
            chart.setData(data, Int::toDouble) { parent, item ->
                SimpleTreemapItemView(parent.context).apply { text = item.toString() }
            }
            activity.setContentView(chart, FrameLayout.LayoutParams(400, 300))
        }

        val bounds = mutableListOf<TreemapNode>()
        scenario.onActivity {
            val root = chart.getChildAt(0) as ViewGroup
            (0..<root.childCount).map(root::getChildAt).forEach { child: View ->
                bounds += TreemapNode(
                    width = child.width,
                    height = child.height,
                    offsetX = child.left,
                    offsetY = child.top,
                )
            }
        }

        assertEquals(SquarifiedMeasurer().measureNodes(listOf(6.0, 3.0, 1.0), 400, 300), bounds)
        scenario.close()
    }

    @Test
    fun testMeasurerChangeLaysOutChartAgain() {
        val data = tree(10) {
            node(6)
            node(3)
            node(1)
        }
        val scenario = ActivityScenario.launch(TreemapChartTestActivity::class.java)
        lateinit var chart: TreemapChartView
        scenario.onActivity { activity ->
            chart = TreemapChartView(activity)
            chart.setData(data, Int::toDouble) { parent, item ->
                SimpleTreemapItemView(parent.context).apply { text = item.toString() }
            }
            activity.setContentView(chart, FrameLayout.LayoutParams(300, 400))
        }

        scenario.onActivity { chart.measurer = SliceAndDiceMeasurer }

        val bounds = mutableListOf<TreemapNode>()
        scenario.onActivity {
            val root = chart.getChildAt(0) as ViewGroup
            (0..<root.childCount).map(root::getChildAt).forEach { child: View ->
                bounds += TreemapNode(
                    width = child.width,
                    height = child.height,
                    offsetX = child.left,
                    offsetY = child.top,
                )
            }
        }
        assertEquals(SliceAndDiceMeasurer.measureNodes(listOf(6.0, 3.0, 1.0), 300, 400), bounds)
        scenario.close()
    }

    @Test
    fun testRtlLayoutDirectionMirrorsNodes() {
        val data = tree(10) {
            node(6)
            node(3)
            node(1)
        }
        val scenario = ActivityScenario.launch(TreemapChartTestActivity::class.java)
        lateinit var chart: TreemapChartView
        scenario.onActivity { activity ->
            chart = TreemapChartView(activity)
            chart.layoutDirection = View.LAYOUT_DIRECTION_RTL
            chart.setData(data, Int::toDouble) { parent, item ->
                SimpleTreemapItemView(parent.context).apply { text = item.toString() }
            }
            activity.setContentView(chart, FrameLayout.LayoutParams(400, 300))
        }

        val bounds = mutableListOf<TreemapNode>()
        scenario.onActivity {
            val root = chart.getChildAt(0) as ViewGroup
            (0..<root.childCount).map(root::getChildAt).forEach { child: View ->
                bounds += TreemapNode(
                    width = child.width,
                    height = child.height,
                    offsetX = child.left,
                    offsetY = child.top,
                )
            }
        }

        val expected = SquarifiedMeasurer().measureNodes(listOf(6.0, 3.0, 1.0), 400, 300).map { node ->
            node.copy(offsetX = 400 - node.offsetX - node.width)
        }
        assertEquals(expected, bounds)
        scenario.close()
    }

    @Test
    fun testNodeViewFactoryWrapsGroupViews() {
        val data = tree("root") {
            node("A") {
                node("A1")
            }
            node("B")
        }
        val scenario = ActivityScenario.launch(TreemapChartTestActivity::class.java)

        scenario.onActivity { activity ->
            val chart = TreemapChartView(activity)
            chart.setNodeData(data, { 1.0 }) { parent, node, groupView ->
                if (node.children.isEmpty()) {
                    SimpleTreemapItemView(parent.context).apply { text = node.data }
                } else {
                    FrameLayout(parent.context).apply {
                        contentDescription = "group ${node.data}"
                        addView(groupView)
                    }
                }
            }
            activity.setContentView(chart)
        }

        onView(withText("A1")).check(matches(isDisplayed()))
        onView(withText("B")).check(matches(isDisplayed()))
        onView(withContentDescription("group A"))
            .check(matches(isDisplayed()))
        scenario.close()
    }
}
