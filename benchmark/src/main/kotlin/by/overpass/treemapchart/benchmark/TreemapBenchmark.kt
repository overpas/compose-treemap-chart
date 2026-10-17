package by.overpass.treemapchart.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.TraceSectionMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE_NAME = "by.overpass.treemapchart.sample.android"
private const val ITERATIONS = 10
private const val CHANGES_PER_ITERATION = 20
private const val RECOMPOSE_SECTION = "Recomposer:recompose"
private const val MEASURE_AND_LAYOUT_SECTION = "AndroidOwner:measureAndLayout"
private const val TRAVERSAL_SECTION = "traversal"

@OptIn(ExperimentalMetricApi::class)
@RunWith(AndroidJUnit4::class)
class TreemapBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun showSimpleChart() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(
                StartupTimingMetric(),
                FrameTimingMetric(),
            ),
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
            metrics = listOf(
                FrameTimingMetric(),
                TraceSectionMetric(RECOMPOSE_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(MEASURE_AND_LAYOUT_SECTION, TraceSectionMetric.Mode.Sum),
            ),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = null,
            iterations = ITERATIONS,
            setupBlock = {
                killProcess()
                startActivityAndWait()
                waitForSimpleChart()
            },
        ) {
            openComplexChart()
        }

    @Test
    fun showComplexChartDetailPopup() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(
                FrameTimingMetric(),
                TraceSectionMetric(RECOMPOSE_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(MEASURE_AND_LAYOUT_SECTION, TraceSectionMetric.Mode.Sum),
            ),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = null,
            iterations = ITERATIONS,
            setupBlock = {
                killProcess()
                startActivityAndWait()
                openComplexChart()
            },
        ) {
            openTopProductPopup()
        }

    @Test
    fun changeLargeChartData() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(
                FrameTimingMetric(),
                TraceSectionMetric(RECOMPOSE_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(MEASURE_AND_LAYOUT_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(TRAVERSAL_SECTION, TraceSectionMetric.Mode.Sum),
            ),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = null,
            iterations = ITERATIONS,
            setupBlock = {
                killProcess()
                startActivityAndWait()
                openLargeChart()
            },
        ) {
            repeat(CHANGES_PER_ITERATION) {
                changeLargeChartData()
            }
        }

    @Test
    fun resizeLargeChart() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(
                FrameTimingMetric(),
                TraceSectionMetric(RECOMPOSE_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(MEASURE_AND_LAYOUT_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(TRAVERSAL_SECTION, TraceSectionMetric.Mode.Sum),
            ),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = null,
            iterations = ITERATIONS,
            setupBlock = {
                killProcess()
                startActivityAndWait()
                openLargeChart()
            },
        ) {
            repeat(CHANGES_PER_ITERATION) {
                resizeLargeChart()
            }
        }

    @Test
    fun changeLargeCanvasChartData() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(
                FrameTimingMetric(),
                TraceSectionMetric(RECOMPOSE_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(MEASURE_AND_LAYOUT_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(TRAVERSAL_SECTION, TraceSectionMetric.Mode.Sum),
            ),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = null,
            iterations = ITERATIONS,
            setupBlock = {
                killProcess()
                startActivityAndWait()
                openCanvasLargeChart()
            },
        ) {
            repeat(CHANGES_PER_ITERATION) {
                changeLargeChartData()
            }
        }

    @Test
    fun resizeLargeCanvasChart() =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(
                FrameTimingMetric(),
                TraceSectionMetric(RECOMPOSE_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(MEASURE_AND_LAYOUT_SECTION, TraceSectionMetric.Mode.Sum),
                TraceSectionMetric(TRAVERSAL_SECTION, TraceSectionMetric.Mode.Sum),
            ),
            compilationMode = CompilationMode.DEFAULT,
            startupMode = null,
            iterations = ITERATIONS,
            setupBlock = {
                killProcess()
                startActivityAndWait()
                openCanvasLargeChart()
            },
        ) {
            repeat(CHANGES_PER_ITERATION) {
                resizeLargeChart()
            }
        }
}
