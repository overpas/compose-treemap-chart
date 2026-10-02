package by.overpass.treemapchart.sample.shared

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class AppUiTest {

    @Test
    fun simpleChartIsShownFirst() =
        runComposeUiTest {
            setAppContent()

            assertSimpleChartDisplayed()
        }

    @Test
    fun complexChartIsShownAfterButtonClick() =
        runComposeUiTest {
            setAppContent()

            openComplexChart()

            assertComplexChartDisplayed()
        }

    @Test
    fun backButtonReturnsToSimpleChart() =
        runComposeUiTest {
            setAppContent()
            openComplexChart()

            clickBack()

            assertSimpleChartDisplayed()
        }

    @Test
    fun productPopupIsShownAfterProductClick() =
        runComposeUiTest {
            setAppContent()
            openComplexChart()

            clickProduct(TOP_PRODUCT)

            assertProductPopupDisplayed(TOP_PRODUCT)
        }

    private companion object {
        const val TOP_PRODUCT = "Cars"
    }
}
