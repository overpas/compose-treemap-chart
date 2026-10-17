package by.overpass.treemapchart.views

import android.view.View
import android.view.View.MeasureSpec
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.tree.tree
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class TreemapChartViewTest {

    private val context = RuntimeEnvironment.getApplication()
    private val sut = TreemapChartView(context)
    private val sampleTreeData = tree(10) {
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

    @Test
    fun itemViewsAreCreatedForTheLeavesOnly() {
        val items = mutableListOf<Int>()
        val expectedItems = listOf(4, 1, 1, 2, 1, 1)

        sut.setData(sampleTreeData, Int::toDouble) { parent, item ->
            items += item
            View(parent.context)
        }

        assertEquals(expectedItems, items)
    }

    @Test
    fun nodeViewsGetAGroupForTheNodesWithChildrenOnly() {
        val groupNodes = mutableListOf<Int>()
        val leafNodes = mutableListOf<Int>()
        val expectedNodes = listOf(2, 6, 3, 10) to listOf(4, 1, 1, 2, 1, 1)

        sut.setData(sampleTreeData, Int::toDouble) { parent, node, group ->
            if (group == null) {
                leafNodes += node.data
                View(parent.context)
            } else {
                groupNodes += node.data
                group
            }
        }
        val nodes = groupNodes to leafNodes

        assertEquals(expectedNodes, nodes)
    }

    @Test
    fun leavesFillTheChartArea() {
        val leaves = mutableListOf<View>()
        sut.setData(sampleTreeData, Int::toDouble) { parent, _ ->
            View(parent.context).also { leaves += it }
        }

        sut.measure(
            MeasureSpec.makeMeasureSpec(301, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(199, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 301, 199)
        val leavesArea = leaves.sumOf { it.width * it.height }

        assertEquals(301 * 199, leavesArea)
    }

    @Test
    fun newDataReplacesTheOldViews() {
        val oldView = View(context)
        val newView = View(context)
        sut.setData(tree(1) {}, Int::toDouble) { _, _ -> oldView }
        val expectedChildren = listOf(newView)

        sut.setData(tree(2) {}, Int::toDouble) { _, _ -> newView }
        val children = (0..<sut.childCount).map(sut::getChildAt)

        assertEquals(expectedChildren, children)
    }

    @Test
    fun newMeasurerMeasuresTheNestedGroups() {
        val leaves = mutableMapOf<Int, View>()
        val data = tree(12) {
            node(6) {
                node(1)
                node(2)
                node(3)
            }
            node(6)
        }
        sut.setData(data, Int::toDouble) { parent, item ->
            View(parent.context).also { leaves[item] = it }
        }
        val expectedSizes = listOf(17 to 100, 33 to 100, 50 to 100)

        sut.measurer = SliceAndDiceMeasurer
        sut.measure(
            MeasureSpec.makeMeasureSpec(200, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 200, 100)
        val sizes = listOf(1, 2, 3).map { leaves.getValue(it).width to leaves.getValue(it).height }

        assertEquals(expectedSizes, sizes)
    }
}
