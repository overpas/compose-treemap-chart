package by.overpass.treemapchart.compose

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

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
    fun nestedGroupsAndLeavesAreDisplayed() =
        runComposeUiTest {
            setTreemapChartGroupContent()

            assertTreemapChartNodesDisplayed()
        }

    @Test
    fun treemapChartFillsItsBounds() =
        runComposeUiTest {
            setTreemapChartContent(SquarifiedMeasurer())

            assertTreemapChartFillsBounds()
        }

    @Test
    fun weightChangeResizesChild() =
        runComposeUiTest {
            val firstWeight = mutableDoubleStateOf(1.0)
            setTreemapChartContentWithFirstWeight(firstWeight)

            firstWeight.doubleValue = 3.0

            assertFirstChildTakesThreeQuarters()
        }

    @Test
    fun childWithoutWeightFails() =
        runComposeUiTest {
            val exception = assertFailsWith<IllegalStateException> {
                setTreemapChartContentWithoutWeight()
            }

            assertEquals("Every direct child of TreemapChart must have Modifier.weight", exception.message)
        }
}
