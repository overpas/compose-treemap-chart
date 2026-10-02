package by.overpass.treemapchart.sample.android

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.test.platform.app.InstrumentationRegistry
import by.overpass.treemapchart.compose.LocalTreemapChartMeasurer
import by.overpass.treemapchart.compose.SimpleTreemapItem
import by.overpass.treemapchart.compose.TreemapChart
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.tree
import by.overpass.treemapchart.view.SimpleTreemapItemView
import by.overpass.treemapchart.view.TreemapChartView
import java.io.File
import kotlin.math.roundToInt

internal val comparisonTreeData = tree(Item(0, 20)) {
    node(Item(1, 8)) {
        node(Item(2, 4))
        node(Item(3, 2))
        node(Item(4, 1))
        node(Item(5, 1))
    }
    node(Item(6, 5)) {
        node(Item(7, 3))
        node(Item(8, 2))
    }
    node(Item(9, 3))
    node(Item(10, 2)) {
        node(Item(11, 1))
        node(Item(12, 1))
    }
    node(Item(13, 1))
    node(Item(14, 1))
}

internal fun Activity.showComparisonScreen(
    composeMeasurer: TreemapChartMeasurer,
    viewMeasurer: TreemapChartMeasurer,
): ComparisonScreen {
    val composeBounds = mutableMapOf<Int, Rect>()
    val composeChart = ComposeView(this).apply {
        setContent {
            CompositionLocalProvider(LocalTreemapChartMeasurer provides composeMeasurer) {
                TreemapChart(
                    data = comparisonTreeData,
                    evaluateItem = { it.value.toDouble() },
                ) { item ->
                    SimpleTreemapItem(
                        item = item.value.toString(),
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            val bounds = coordinates.boundsInRoot()
                            composeBounds[item.id] = Rect(
                                bounds.left.roundToInt(),
                                bounds.top.roundToInt(),
                                bounds.right.roundToInt(),
                                bounds.bottom.roundToInt(),
                            )
                        },
                    )
                }
            }
        }
    }
    val lightContext = ContextThemeWrapper(this, android.R.style.Theme_Material_Light_NoActionBar)
    val viewChart = TreemapChartView(lightContext).apply {
        measurer = viewMeasurer
        setData(comparisonTreeData, { it.value.toDouble() }) { parent, item ->
            SimpleTreemapItemView(parent.context).apply {
                text = item.value.toString()
                tag = item.id
            }
        }
    }
    val root = LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        isBaselineAligned = false
        setBackgroundColor(Color.WHITE)
        addView(column("Compose", composeChart), columnParams())
        addView(column("View", viewChart), columnParams())
    }
    setContentView(root)
    return ComparisonScreen(root, composeChart, viewChart, composeBounds)
}

private fun Activity.column(
    title: String,
    chart: View,
): View =
    LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        val padding = (8 * resources.displayMetrics.density).roundToInt()
        setPadding(padding, padding, padding, padding)
        addView(
            TextView(this@column).apply {
                text = title
                gravity = Gravity.CENTER
                setTextColor(Color.BLACK)
            },
        )
        addView(chart, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f))
    }

private fun columnParams() =
    LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f)

internal fun ComparisonScreen.viewBounds(): Map<Int, Rect> {
    val chartLocation = IntArray(2)
    viewChart.getLocationInWindow(chartLocation)
    return viewChart.leafViews().associate { leaf ->
        val location = IntArray(2)
        leaf.getLocationInWindow(location)
        val left = location[0] - chartLocation[0]
        val top = location[1] - chartLocation[1]
        leaf.tag as Int to Rect(left, top, left + leaf.width, top + leaf.height)
    }
}

private fun View.leafViews(): List<SimpleTreemapItemView> =
    when (this) {
        is SimpleTreemapItemView -> listOf(this)
        is ViewGroup -> (0..<childCount).flatMap { getChildAt(it).leafViews() }
        else -> emptyList()
    }

internal fun ComparisonScreen.saveScreenshot(name: String): File {
    val instrumentation = InstrumentationRegistry.getInstrumentation()
    val screenshot = instrumentation.uiAutomation.takeScreenshot()
    val location = IntArray(2)
    root.getLocationOnScreen(location)
    val width = root.width.coerceAtMost(screenshot.width - location[0])
    val height = root.height.coerceAtMost(screenshot.height - location[1])
    val cropped = Bitmap.createBitmap(screenshot, location[0], location[1], width, height)
    val outputDir = InstrumentationRegistry.getArguments().getString("additionalTestOutputDir")
        ?.let(::File)
        ?: File(instrumentation.targetContext.externalMediaDirs.first(), "additional_test_output")
    outputDir.mkdirs()
    val file = File(outputDir, "$name.png")
    file.outputStream().use { cropped.compress(Bitmap.CompressFormat.PNG, 100, it) }
    Log.i("ComparisonScreenshot", file.absolutePath)
    return file
}
