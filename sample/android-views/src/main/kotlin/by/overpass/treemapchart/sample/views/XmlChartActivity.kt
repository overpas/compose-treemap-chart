package by.overpass.treemapchart.sample.views

import android.app.Activity
import android.os.Bundle

class XmlChartActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_chart)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
