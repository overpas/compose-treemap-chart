package by.overpass.treemapchart.core.measure

interface TreemapChartMeasurer {

    fun measureNodes(values: List<Double>, width: Int, height: Int): List<TreemapNode>
}