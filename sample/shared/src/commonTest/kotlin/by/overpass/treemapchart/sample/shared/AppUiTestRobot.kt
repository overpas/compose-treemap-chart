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

private const val SIMPLE_CHART_TITLE = "Simple chart"
private const val COMPLEX_CHART_TITLE = "Japan Exports 2021"
private const val COMPLEX_CHART_BUTTON = "Show more complex chart"
private const val PARSING_TEXT = "Parsing Exports data..."
private const val EXPORTS_VALUE_LABEL = "Exports value"
private const val PERCENTAGE_LABEL = "Percentage"
private const val BACK_DESCRIPTION = "Back"
private const val LOADING_TIMEOUT_MILLIS = 30_000L

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.setAppContent() {
    setContent {
        App()
    }
}

@OptIn(ExperimentalTestApi::class)
internal fun ComposeUiTest.openComplexChart() {
    onNodeWithText(COMPLEX_CHART_BUTTON)
        .performClick()
    waitUntil(timeoutMillis = LOADING_TIMEOUT_MILLIS) {
        onAllNodesWithText(PARSING_TEXT)
            .fetchSemanticsNodes()
            .isEmpty()
    }
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
