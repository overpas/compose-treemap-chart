package by.overpass.treemapchart.sample.views

import android.app.Activity
import android.os.Bundle
import android.os.Trace
import android.view.View
import android.view.ViewGroup
import by.overpass.treemapchart.views.TreemapChartView
import kotlin.math.roundToInt

private const val SHRUNK_CHART_FRACTION = 0.7f
private const val SEED_KEY = "seed"
private const val IS_SHRUNK_KEY = "isShrunk"
private const val SET_DATA_SECTION = "TreemapChartView:setData"
private const val LEAF_BORDER_WIDTH_DP = 0.5f

class LargeChartActivity : Activity() {

    private val state = Bundle()

    private val seed: Int
        get() = state.getInt(SEED_KEY)

    private val isShrunk: Boolean
        get() = state.getBoolean(IS_SHRUNK_KEY)

    private val chartContainer: View
        get() = findViewById(R.id.chart_container)

    private val chart: TreemapChartView
        get() = findViewById(R.id.chart)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_large_chart)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        savedInstanceState?.let(state::putAll)
        showData()
        chartContainer.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> resizeChart() }
        findViewById<View>(R.id.change_data).setOnClickListener {
            state.putInt(SEED_KEY, seed + 1)
            showData()
        }
        findViewById<View>(R.id.resize).setOnClickListener {
            state.putBoolean(IS_SHRUNK_KEY, !isShrunk)
            resizeChart()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(state)
    }

    private fun showData() {
        Trace.beginSection(SET_DATA_SECTION)
        try {
            chart.setData(LargeTreeGenerator.generate(seed), { it }) { parent, _ ->
                View(parent.context).apply { background = BorderDrawable(parent.context, LEAF_BORDER_WIDTH_DP) }
            }
        } finally {
            Trace.endSection()
        }
    }

    private fun resizeChart() {
        val width = if (isShrunk) {
            (chartContainer.width * SHRUNK_CHART_FRACTION).roundToInt()
        } else {
            ViewGroup.LayoutParams.MATCH_PARENT
        }
        val height = if (isShrunk) {
            (chartContainer.height * SHRUNK_CHART_FRACTION).roundToInt()
        } else {
            ViewGroup.LayoutParams.MATCH_PARENT
        }
        val layoutParams = chart.layoutParams
        if (layoutParams.width != width || layoutParams.height != height) {
            layoutParams.width = width
            layoutParams.height = height
            chart.layoutParams = layoutParams
        }
    }
}
