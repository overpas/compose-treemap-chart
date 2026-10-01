package by.overpass.treemapchart.compose

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.tree

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

internal fun ComposeContentTestRule.setTreemapChartContent(measurer: TreemapChartMeasurer) {
    setContent {
        CompositionLocalProvider(LocalTreemapChartMeasurer provides measurer) {
            TreemapChart(
                data = sampleTreeData,
                evaluateItem = Int::toDouble,
            ) { item ->
                SimpleTreemapItem(item.toString())
            }
        }
    }
}

internal fun ComposeContentTestRule.assertTreemapChartDisplayed() {
    onAllNodesWithText("1")
        .assertCountEquals(4)
    onNodeWithText("2")
        .assertExists()
    onNodeWithText("4")
        .assertExists()
}
