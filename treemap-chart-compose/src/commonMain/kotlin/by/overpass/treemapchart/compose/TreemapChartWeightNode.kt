package by.overpass.treemapchart.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.unit.Density

internal class TreemapChartWeightNode(var weight: Double) :
    Modifier.Node(),
    ParentDataModifierNode {

    override fun Density.modifyParentData(parentData: Any?): Any =
        this@TreemapChartWeightNode
}
