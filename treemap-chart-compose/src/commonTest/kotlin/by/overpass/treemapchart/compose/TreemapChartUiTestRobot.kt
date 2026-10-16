package by.overpass.treemapchart.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DoubleState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import kotlin.test.assertEquals

private const val CHART_TAG = "chart"
private const val GROUP_TAG = "group"
private const val LEAF_TAG = "leaf"
private const val FIRST_TAG = "first"

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setTreemapChartContent(measurer: TreemapChartMeasurer) {
    setContent {
        CompositionLocalProvider(LocalTreemapChartMeasurer provides measurer) {
            TreemapChart(
                Modifier
                    .testTag(CHART_TAG)
                    .size(width = 301.dp, height = 199.dp),
            ) {
                TreemapChart(Modifier.weight(6.0)) {
                    BasicText(text = "4", modifier = Modifier.weight(4.0).testTag(LEAF_TAG))
                    TreemapChart(Modifier.weight(2.0)) {
                        BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
                        BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
                    }
                }
                TreemapChart(Modifier.weight(3.0)) {
                    BasicText(text = "2", modifier = Modifier.weight(2.0).testTag(LEAF_TAG))
                    BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
                }
                BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
            }
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setTreemapChartGroupContent() {
    setContent {
        TreemapChart {
            Box(Modifier.weight(6.0).testTag(GROUP_TAG)) {
                TreemapChart {
                    BasicText(text = "4", modifier = Modifier.weight(4.0).testTag(LEAF_TAG))
                    Box(Modifier.weight(2.0).testTag(GROUP_TAG)) {
                        TreemapChart {
                            BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
                            BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
                        }
                    }
                }
            }
            Box(Modifier.weight(3.0).testTag(GROUP_TAG)) {
                TreemapChart {
                    BasicText(text = "2", modifier = Modifier.weight(2.0).testTag(LEAF_TAG))
                    BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
                }
            }
            BasicText(text = "1", modifier = Modifier.weight(1.0).testTag(LEAF_TAG))
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setTreemapChartContentWithoutWeight() {
    setContent {
        TreemapChart {
            BasicText(text = "4", modifier = Modifier.weight(4.0))
            BasicText(text = "1")
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setTreemapChartContentWithFirstWeight(firstWeight: DoubleState) {
    setContent {
        CompositionLocalProvider(LocalTreemapChartMeasurer provides SliceAndDiceMeasurer) {
            TreemapChart(Modifier.size(width = 400.dp, height = 100.dp)) {
                BasicText(text = "first", modifier = Modifier.weight(firstWeight.doubleValue).testTag(FIRST_TAG))
                BasicText(text = "second", modifier = Modifier.weight(1.0))
            }
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertFirstChildTakesThreeQuarters() {
    onNodeWithTag(FIRST_TAG)
        .assertWidthIsEqualTo(300.dp)
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
        .assertCountEquals(3)
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
