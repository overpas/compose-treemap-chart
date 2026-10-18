package by.overpass.treemapchart.sample.shared

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

private const val SIMPLE_CHART_TITLE = "Simple chart"
private const val COMPLEX_CHART_TITLE = "Japan Exports 2021"
private const val COMPLEX_CHART_BUTTON = "Show more complex chart"
private const val LARGE_CHART_TITLE = "Large chart"
private const val LARGE_CHART_BUTTON = "Show large chart"
private const val CANVAS_LARGE_CHART_TITLE = "Large canvas chart"
private const val CANVAS_LARGE_CHART_BUTTON = "Show large canvas chart"
private const val CHANGE_DATA_BUTTON = "Change data"
private const val RESIZE_BUTTON = "Resize"
private const val PARSING_TEXT = "Parsing Exports data..."
private const val EXPORTS_VALUE_LABEL = "Exports value"
private const val PERCENTAGE_LABEL = "Percentage"
private const val BACK_DESCRIPTION = "Back"
private val LOADING_POLL_INTERVAL = 50.milliseconds

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setAppContent() {
    setContent {
        App()
    }
}

@OptIn(ExperimentalTestApi::class)
internal suspend fun ComposeUiTest.openComplexChart(pollingDispatcher: CoroutineDispatcher = Dispatchers.Default) {
    onNodeWithText(COMPLEX_CHART_BUTTON)
        .performClick()
    while (onAllNodesWithText(PARSING_TEXT).fetchSemanticsNodes().isNotEmpty()) {
        withContext(pollingDispatcher) {
            delay(LOADING_POLL_INTERVAL)
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.openLargeChart() {
    onNodeWithText(LARGE_CHART_BUTTON)
        .performClick()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.openCanvasLargeChart() {
    onNodeWithText(CANVAS_LARGE_CHART_BUTTON)
        .performClick()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.clickBack() {
    onNodeWithContentDescription(BACK_DESCRIPTION)
        .performClick()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.clickProduct(name: String) {
    onNode(hasProductName(name))
        .performClick()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertSimpleChartDisplayed() {
    onNodeWithText(SIMPLE_CHART_TITLE)
        .assertIsDisplayed()
    onNodeWithText(COMPLEX_CHART_BUTTON)
        .assertIsDisplayed()
    onAllNodesWithText("1")
        .assertCountEquals(4)
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertComplexChartDisplayed() {
    onNodeWithText(COMPLEX_CHART_TITLE)
        .assertIsDisplayed()
    onNodeWithContentDescription(BACK_DESCRIPTION)
        .assertIsDisplayed()
    onNode(hasProductName("Cars"))
        .assertIsDisplayed()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertLargeChartDisplayed() {
    onNodeWithText(LARGE_CHART_TITLE)
        .assertIsDisplayed()
    onNodeWithContentDescription(BACK_DESCRIPTION)
        .assertIsDisplayed()
    onNodeWithText(CHANGE_DATA_BUTTON)
        .assertIsDisplayed()
    onNodeWithText(RESIZE_BUTTON)
        .assertIsDisplayed()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertCanvasLargeChartDisplayed() {
    onNodeWithText(CANVAS_LARGE_CHART_TITLE)
        .assertIsDisplayed()
    onNodeWithContentDescription(BACK_DESCRIPTION)
        .assertIsDisplayed()
    onNodeWithText(CHANGE_DATA_BUTTON)
        .assertIsDisplayed()
    onNodeWithText(RESIZE_BUTTON)
        .assertIsDisplayed()
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.assertProductPopupDisplayed(name: String) {
    onNodeWithContentDescription(name)
        .assertIsDisplayed()
    onNodeWithText(EXPORTS_VALUE_LABEL)
        .assertIsDisplayed()
    onNodeWithText(PERCENTAGE_LABEL)
        .assertIsDisplayed()
}

private fun hasProductName(name: String): SemanticsMatcher =
    SemanticsMatcher("Product item $name") { node ->
        node.config
            .getOrNull(SemanticsProperties.Text)
            .orEmpty()
            .any { it.text.startsWith("$name\n") }
    }
