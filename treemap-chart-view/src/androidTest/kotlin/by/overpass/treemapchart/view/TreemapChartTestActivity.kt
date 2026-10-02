package by.overpass.treemapchart.view

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup

class TreemapChartTestActivity : Activity() {

    lateinit var chart: TreemapChartView
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chart = TreemapChartView(this)
        setContentView(
            chart,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT),
        )
    }
}
