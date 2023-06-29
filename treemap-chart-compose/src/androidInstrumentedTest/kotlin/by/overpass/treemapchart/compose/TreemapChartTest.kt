package by.overpass.treemapchart.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.tree
import org.junit.Rule
import org.junit.Test

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

class TreemapChartTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSliceAndDiceTreemapDisplayed() {
        testTreemapDisplayed(SliceAndDiceMeasurer)
    }

    @Test
    fun testSquarifiedTreemapDisplayed() {
        testTreemapDisplayed(SquarifiedMeasurer())
    }

    private fun testTreemapDisplayed(measurer: TreemapChartMeasurer) {
        composeTestRule.setContent {
            MaterialTheme {
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

        composeTestRule.onAllNodesWithText("1")
            .assertCountEquals(4)

        composeTestRule.onNodeWithText("2")
            .assertExists()

        composeTestRule.onNodeWithText("4")
            .assertExists()
    }
}

