package by.overpass.treemapchart.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.tree
import kotlin.test.assertEquals

private const val CHART_TAG = "chart"
private const val GROUP_TAG = "group"
private const val LEAF_TAG = "leaf"

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

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setTreemapChartContent(measurer: TreemapChartMeasurer) {
    setContent {
        CompositionLocalProvider(LocalTreemapChartMeasurer provides measurer) {
            TreemapChart(
                data = sampleTreeData,
                evaluateItem = Int::toDouble,
                modifier = Modifier
                    .testTag(CHART_TAG)
                    .size(width = 301.dp, height = 199.dp),
            ) { item ->
                SimpleTreemapItem(
                    item = item.toString(),
                    modifier = Modifier.testTag(LEAF_TAG),
                )
            }
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setTreemapChartNodeContent() {
    setContent {
        TreemapChart(
            data = sampleTreeData,
            evaluateItem = Int::toDouble,
        ) { node, groupContent ->
            if (node.children.isEmpty()) {
                Text(
                    text = node.data.toString(),
                    modifier = Modifier.testTag(LEAF_TAG),
                )
            } else {
                Box(Modifier.testTag(GROUP_TAG)) {
                    groupContent(node)
                }
            }
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertTreemapChartDisplayed() {
    onAllNodesWithText("1")
        .assertCountEquals(4)
    onNodeWithText("2")
        .assertExists()
    onNodeWithText("4")
        .assertExists()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertTreemapChartNodesDisplayed() {
    onAllNodesWithTag(GROUP_TAG)
        .assertCountEquals(4)
    onAllNodesWithTag(LEAF_TAG)
        .assertCountEquals(6)
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertTreemapChartFillsBounds() {
    val chartSize = onNodeWithTag(CHART_TAG)
        .fetchSemanticsNode()
        .size
    val leavesArea = onAllNodesWithTag(LEAF_TAG)
        .fetchSemanticsNodes()
        .sumOf { it.size.width * it.size.height }
    assertEquals(
        expected = chartSize.width * chartSize.height,
        actual = leavesArea,
    )
}
