package by.overpass.treemapchart.benchmark.views

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

private const val TIMEOUT_MILLIS = 5_000L
private const val LARGE_CHART_BUTTON = "Show large chart"
private const val CHANGE_DATA_BUTTON = "Change data"
private const val RESIZE_BUTTON = "Resize"

internal fun MacrobenchmarkScope.waitForSimpleChart() {
    waitForObject(By.text("4"))
    waitForObject(By.text("2"))
    waitForObject(By.text("1"))
}

internal fun MacrobenchmarkScope.openLargeChart() {
    waitForObject(By.text(LARGE_CHART_BUTTON)).click()
    waitForObject(By.text(CHANGE_DATA_BUTTON))
}

internal fun MacrobenchmarkScope.changeLargeChartData() {
    waitForObject(By.text(CHANGE_DATA_BUTTON)).click()
    device.waitForIdle()
}

internal fun MacrobenchmarkScope.resizeLargeChart() {
    waitForObject(By.text(RESIZE_BUTTON)).click()
    device.waitForIdle()
}

private fun MacrobenchmarkScope.waitForObject(selector: BySelector): UiObject2 =
    checkNotNull(device.wait(Until.findObject(selector), TIMEOUT_MILLIS)) {
        "No object matches $selector"
    }
