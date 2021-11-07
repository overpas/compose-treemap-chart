package by.overpass.treemapchart.android

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import org.junit.Rule
import org.junit.Test

class TreemapChartTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSliceAndDiceTreemapDisplayed() {
        testTreemapDisplayed(SliceAndDiceMeasurer())
    }

    @Test
    fun testSquarifiedTreemapDisplayed() {
        testTreemapDisplayed(SquarifiedMeasurer())
    }

    private fun testTreemapDisplayed(measurer: TreemapChartMeasurer) {
        composeTestRule.setContent {
            MaterialTheme {
                TreemapChart(
                    data = sampleTreeData,
                    evaluateItem = { it.toDouble() },
                    treemapChartMeasurer = remember { measurer }
                ) {
                    SimpleTreemapItem(it)
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