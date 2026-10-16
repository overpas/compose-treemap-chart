package by.overpass.treemapchart.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE_NAME = "by.overpass.treemapchart.sample.android"
private const val ITERATIONS = 5

@RunWith(AndroidJUnit4::class)
class TreemapBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun showSimpleChart() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(FrameTimingMetric()),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = StartupMode.COLD,
            iterations = ITERATIONS,
        ) {
            startActivityAndWait()

            waitForSimpleChart()
        }

    @Test
    fun showComplexChart() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(FrameTimingMetric()),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = StartupMode.COLD,
            iterations = ITERATIONS,
        ) {
            startActivityAndWait()

            openComplexChart()
        }

    @Test
    fun showComplexChartDetailPopup() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(FrameTimingMetric()),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = StartupMode.COLD,
            iterations = ITERATIONS,
        ) {
            startActivityAndWait()
            openComplexChart()

            openTopProductPopup()
        }
}
