package by.overpass.treemapchart.compose

import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo

internal data class TreemapChartWeightElement(val weight: Double) : ModifierNodeElement<TreemapChartWeightNode>() {

    override fun create(): TreemapChartWeightNode =
        TreemapChartWeightNode(weight)

    override fun update(node: TreemapChartWeightNode) {
        node.weight = weight
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "weight"
        value = weight
    }
}
