package by.overpass.treemapchart.benchmark.views

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

private const val TIMEOUT_MILLIS = 5_000L
private const val LARGE_CHART_BUTTON = "Show large chart"
private const val CHANGE_DATA_BUTTON = "Change data"
private const val RESIZE_BUTTON = "Resize"

internal fun MacrobenchmarkScope.waitForSimpleChart() {
    By.text("4").waitForObject(device)
    By.text("2").waitForObject(device)
    By.text("1").waitForObject(device)
}

internal fun MacrobenchmarkScope.openLargeChart() {
    By.text(LARGE_CHART_BUTTON).waitForObject(device).click()
    By.text(CHANGE_DATA_BUTTON).waitForObject(device)
}

internal fun MacrobenchmarkScope.changeLargeChartData() {
    By.text(CHANGE_DATA_BUTTON).waitForObject(device).click()
    device.waitForIdle()
}

internal fun MacrobenchmarkScope.resizeLargeChart() {
    By.text(RESIZE_BUTTON).waitForObject(device).click()
    device.waitForIdle()
}

private fun BySelector.waitForObject(device: UiDevice): UiObject2 =
    checkNotNull(device.wait(Until.findObject(this), TIMEOUT_MILLIS)) {
        "No object matches $this"
    }
