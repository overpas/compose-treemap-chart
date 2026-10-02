package by.overpass.treemapchart.view

import android.view.View
import android.view.ViewGroup
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.tree.Tree

internal class TreemapNodeViewBuilder<T>(
    private val measurer: TreemapChartMeasurer,
    private val evaluator: TreemapItemEvaluator<T>,
    private val nodeViewFactory: TreemapNodeViewFactory<T>,
) {

    fun build(
        parent: ViewGroup,
        node: Tree.Node<T>,
    ): View {
        val groupView = if (node.children.isEmpty()) null else buildGroup(parent, node)
        return nodeViewFactory.create(parent, node, groupView)
    }

    private fun buildGroup(
        parent: ViewGroup,
        node: Tree.Node<T>,
    ): ViewGroup {
        val layout = TreemapChartLayout(
            context = parent.context,
            measurer = measurer,
            values = node.children.map { evaluator.evaluate(it.data) },
        )
        node.children.forEach { child ->
            layout.addView(build(layout, child))
        }
        return layout
    }
}
