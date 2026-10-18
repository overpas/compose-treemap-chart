package by.overpass.treemapchart.sample.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import by.overpass.treemapchart.core.tree.tree
import by.overpass.treemapchart.views.TreemapChartView

private const val ITEM_BORDER_WIDTH_DP = 1f

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

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TreemapChartView>(R.id.chart).setData(simpleTreeData, Int::toDouble) { parent, item ->
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_simple, parent, false)
            check(itemView is TextView)
            itemView.text = item.toString()
            itemView.background = BorderDrawable(this, ITEM_BORDER_WIDTH_DP)
            itemView
        }
        findViewById<View>(R.id.show_xml_chart).setOnClickListener {
            startActivity(Intent(this, XmlChartActivity::class.java))
        }
        findViewById<View>(R.id.show_large_chart).setOnClickListener {
            startActivity(Intent(this, LargeChartActivity::class.java))
        }
    }
}
