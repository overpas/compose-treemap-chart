package by.overpass.treemapchart.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.Tree

internal class TreemapChartViewFactory<T>(
    private val context: Context,
    private val measurer: TreemapChartMeasurer,
    private val evaluateItem: (T) -> Double,
    private val createNodeView: (parent: ViewGroup, node: Tree.Node<T>, group: TreemapChartLayout?) -> View,
) {

    val groups = mutableListOf<TreemapChartLayout>()

    fun create(
        parent: ViewGroup,
        node: Tree.Node<T>,
    ): View {
        if (node.children.isEmpty()) {
            return createNodeView(parent, node, null)
        }
        val group = TreemapChartLayout(context)
        group.measurer = measurer
        groups += group
        node.children.forEach { child ->
            group.addView(
                create(group, child),
                TreemapChartLayout.LayoutParams(
                    width = ViewGroup.LayoutParams.MATCH_PARENT,
                    height = ViewGroup.LayoutParams.MATCH_PARENT,
                    weight = evaluateItem(child.data),
                ),
            )
        }
        return createNodeView(parent, node, group)
    }
}
