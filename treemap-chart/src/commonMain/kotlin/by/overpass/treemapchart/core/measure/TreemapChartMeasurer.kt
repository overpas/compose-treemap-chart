package by.overpass.treemapchart.core.measure

/**
 * Describes the strategy used to measure treemap nodes
 */
interface TreemapChartMeasurer {

    /**
     * @param values nodes' weights/areas
     * @param width max treemap width
     * @param height max treemap height
     * @return list of [TreemapNode]s containing information on how the nodes should be positioned
     */
    fun measureNodes(values: List<Double>, width: Int, height: Int): List<TreemapNode>
}
