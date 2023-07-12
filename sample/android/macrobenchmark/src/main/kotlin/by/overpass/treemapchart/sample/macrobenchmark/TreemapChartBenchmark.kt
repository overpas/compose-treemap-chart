package by.overpass.treemapchart.sample.macrobenchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TreemapChartBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun showSimpleChart() = measureRepeated {
        startActivityAndWait()

        device.wait(Until.hasObject(By.text("4")), 1000)
        device.wait(Until.hasObject(By.text("2")), 1000)
        device.wait(Until.hasObject(By.text("1")), 1000)
    }

    @Test
    fun showComplexChart() = benchmarkRule.measureRepeated(
        packageName = "by.overpass.treemapchart.sample.android",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.DEFAULT,
        startupMode = StartupMode.COLD,
        iterations = 5,
    ) {
        startActivityAndWait()

        device.findObject(By.text("Show more complex chart"))
            .click()

        device.wait(
            Until.hasObject(
                By.text(
                    """
                    |Cars
                    |12.11%
                    """.trimMargin(),
                ),
            ), 5000
        )
    }

    @Test
    fun showComplexChartDetailPopup() = benchmarkRule.measureRepeated(
        packageName = "by.overpass.treemapchart.sample.android",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.DEFAULT,
        startupMode = StartupMode.COLD,
        iterations = 5,
    ) {
        startActivityAndWait()

        device.findObject(By.text("Show more complex chart"))
            .click()

        device.findObject(
            By.text(
                """
                |Cars
                |12.11%
                """.trimMargin(),
            ),
        ).click()

        device.wait(Until.hasObject(By.text("Exports value")), 5000)
        device.wait(Until.hasObject(By.text("$88.6B")), 5000)
    }

    private fun measureRepeated(block: MacrobenchmarkScope.() -> Unit) {
        benchmarkRule.measureRepeated(
            packageName = "by.overpass.treemapchart.sample.android",
            metrics = listOf(FrameTimingMetric()),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = StartupMode.COLD,
            iterations = 5,
            measureBlock = block,
        )
    }
}
