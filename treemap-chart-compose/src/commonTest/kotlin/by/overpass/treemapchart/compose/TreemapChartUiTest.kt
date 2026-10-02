package by.overpass.treemapchart.compose

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TreemapChartUiTest {

    @Test
    fun sliceAndDiceTreemapIsDisplayed() =
        runComposeUiTest {
            setTreemapChartContent(SliceAndDiceMeasurer)

            assertTreemapChartDisplayed()
        }

    @Test
    fun squarifiedTreemapIsDisplayed() =
        runComposeUiTest {
            setTreemapChartContent(SquarifiedMeasurer())

            assertTreemapChartDisplayed()
        }

    @Test
    fun nodeContentIsDisplayedForGroupsAndLeaves() =
        runComposeUiTest {
            setTreemapChartNodeContent()

            assertTreemapChartNodesDisplayed()
        }

    @Test
    fun treemapChartFillsItsBounds() =
        runComposeUiTest {
            setTreemapChartContent(SquarifiedMeasurer())

            assertTreemapChartFillsBounds()
        }
}
