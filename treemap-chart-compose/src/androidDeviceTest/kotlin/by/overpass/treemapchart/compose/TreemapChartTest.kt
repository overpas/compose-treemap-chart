package by.overpass.treemapchart.compose

import androidx.compose.ui.test.junit4.v2.createComposeRule
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import org.junit.Rule
import org.junit.Test

class TreemapChartTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSliceAndDiceTreemapDisplayed() {
        composeTestRule.setTreemapChartContent(SliceAndDiceMeasurer)

        composeTestRule.assertTreemapChartDisplayed()
    }

    @Test
    fun testSquarifiedTreemapDisplayed() {
        composeTestRule.setTreemapChartContent(SquarifiedMeasurer())

        composeTestRule.assertTreemapChartDisplayed()
    }
}
