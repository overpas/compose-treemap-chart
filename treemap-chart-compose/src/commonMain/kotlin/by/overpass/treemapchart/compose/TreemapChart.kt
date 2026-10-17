package by.overpass.treemapchart.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints

/**
 * Treemap chart UI. Every direct child takes an area in proportion to its
 * [TreemapChartScope.weight]. Put a [TreemapChart] in another [TreemapChart] to make a group.
 *
 * @param modifier Modifier to be applied to the layout
 * @param content The nodes of the chart; Every direct child must have a [TreemapChartScope.weight]
 */
@Composable
fun TreemapChart(
    modifier: Modifier = Modifier,
    content: @Composable TreemapChartScope.() -> Unit,
) {
    val treemapChartMeasurer = LocalTreemapChartMeasurer.current
    Layout(
        content = { TreemapChartScopeInstance.content() },
        modifier = modifier,
    ) { measurables, constraints ->
        val nodes = treemapChartMeasurer.measureNodes(
            measurables.map { it.treemapChartWeight },
            constraints.maxWidth,
            constraints.maxHeight,
        )
        val placeables = measurables.mapIndexed { index, measurable ->
            measurable.measure(Constraints.fixed(nodes[index].width, nodes[index].height))
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(nodes[index].offsetX, nodes[index].offsetY)
            }
        }
    }
}

private val Measurable.treemapChartWeight: Double
    get() {
        val weightNode = parentData
        check(weightNode is TreemapChartWeightNode) {
            "Every direct child of TreemapChart must have Modifier.weight"
        }
        return weightNode.weight
    }
