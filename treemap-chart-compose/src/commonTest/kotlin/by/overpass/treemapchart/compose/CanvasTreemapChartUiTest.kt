package by.overpass.treemapchart.compose

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.tree.tree
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class CanvasTreemapChartUiTest {

    @Test
    fun chartIsDisplayed() =
        runComposeUiTest {
            val data = tree(0.0) {
                node(1.0)
                node(1.0)
            }

            setContent {
                CanvasTreemapChart(
                    data = data,
                    evaluateItem = { it },
                    modifier = Modifier
                        .testTag("chart")
                        .size(width = 301.dp, height = 199.dp),
                ) {}
            }

            onNodeWithTag("chart").assertExists()
        }

    @Test
    fun everyNodeIsDrawnInPreOrder() =
        runComposeUiTest {
            val data = tree(0.0) {
                node(4.0) {
                    node(1.0)
                    node(3.0)
                }
                node(2.0)
            }
            val drawn = mutableListOf<Double>()

            setContent {
                CanvasTreemapChart(
                    data = data,
                    evaluateItem = { it },
                    modifier = Modifier.size(width = 301.dp, height = 199.dp),
                ) { node ->
                    drawn += node.data
                }
            }
            waitForIdle()

            assertEquals(listOf(0.0, 4.0, 1.0, 3.0, 2.0), drawn.take(5))
        }

    @Test
    fun nodeIsDrawnInItsOwnSize() =
        runComposeUiTest {
            val data = tree(0.0) {
                node(3.0)
                node(1.0)
            }
            val sizes = mutableMapOf<Double, Size>()

            setContent {
                CompositionLocalProvider(LocalTreemapChartMeasurer provides SliceAndDiceMeasurer) {
                    CanvasTreemapChart(
                        data = data,
                        evaluateItem = { it },
                        modifier = Modifier
                            .testTag("chart")
                            .size(width = 200.dp, height = 100.dp),
                    ) { node ->
                        sizes[node.data] = size
                    }
                }
            }
            waitForIdle()
            val chartSize = onNodeWithTag("chart").fetchSemanticsNode().size

            assertEquals(Size(chartSize.width.toFloat(), chartSize.height.toFloat()), sizes[0.0])
            assertEquals(
                Size((chartSize.width * 3 / 4.0).roundToInt().toFloat(), chartSize.height.toFloat()),
                sizes[3.0],
            )
        }

    @Test
    fun newTreeIsDrawn() =
        runComposeUiTest {
            var data by mutableStateOf(
                tree(0.0) {
                    node(1.0)
                },
            )
            val drawn = mutableListOf<Double>()
            setContent {
                CanvasTreemapChart(
                    data = data,
                    evaluateItem = { it },
                    modifier = Modifier.size(width = 301.dp, height = 199.dp),
                ) { node ->
                    drawn += node.data
                }
            }
            waitForIdle()

            data = tree(10.0) {
                node(5.0)
                node(2.0)
            }
            waitForIdle()

            assertEquals(listOf(10.0, 5.0, 2.0), drawn.takeLast(3))
        }
}
