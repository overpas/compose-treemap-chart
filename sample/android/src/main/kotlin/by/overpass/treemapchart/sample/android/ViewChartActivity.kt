package by.overpass.treemapchart.sample.android

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.widget.LinearLayout
import android.widget.Toolbar
import by.overpass.treemapchart.core.tree.tree
import by.overpass.treemapchart.view.SimpleTreemapItemView
import by.overpass.treemapchart.view.TreemapChartView

private val simpleTreeData = tree(10) {
    node(6) {
        node(4)
        node(2) {
            node(1)
            node(1)
        }
    }
    node(3) {
        node(2)
        node(1)
    }
    node(1)
}

class ViewChartActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = Toolbar(this).apply {
            title = "Simple chart (Views)"
            setTitleTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(0x62, 0x00, 0xEE))
        }
        val chart = TreemapChartView(this).apply {
            setData(simpleTreeData, Int::toDouble) { parent, item ->
                SimpleTreemapItemView(parent.context).apply {
                    text = item.toString()
                }
            }
        }
        setContentView(
            LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.WHITE)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    setOnApplyWindowInsetsListener { view, insets ->
                        view.setPadding(0, 0, 0, insets.getInsets(WindowInsets.Type.systemBars()).bottom)
                        insets
                    }
                }
                addView(toolbar)
                addView(chart, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))
            },
        )
    }
}
