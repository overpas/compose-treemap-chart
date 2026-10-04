package by.overpass.treemapchart.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.Tree

class TreemapChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    var measurer: TreemapChartMeasurer = SquarifiedMeasurer()
        set(value) {
            field = value
            forceLayoutOfChildren(this)
            requestLayout()
        }

    fun <T> setData(
        data: Tree<T>,
        evaluator: TreemapItemEvaluator<T>,
        itemViewFactory: TreemapItemViewFactory<T>,
    ) {
        setNodeData(data, evaluator) { parent, node, groupView ->
            if (node.children.isEmpty()) {
                itemViewFactory.create(parent, node.data)
            } else {
                groupView
            }
        }
    }

    fun <T> setNodeData(
        data: Tree<T>,
        evaluator: TreemapItemEvaluator<T>,
        nodeViewFactory: TreemapNodeViewFactory<T>,
    ) {
        fun createNodeView(
            parent: ViewGroup,
            node: Tree.Node<T>,
        ): View {
            val groupView = TreemapChartLayout(
                context = context,
                values = node.children.map { evaluator.evaluate(it.data) },
                measurer = { measurer },
            )
            node.children.forEach { child ->
                groupView.addView(createNodeView(groupView, child))
            }
            return nodeViewFactory.create(parent, node, groupView)
        }
        removeAllViews()
        addView(
            createNodeView(this, data.root),
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT),
        )
    }

    private fun forceLayoutOfChildren(view: View) {
        view.forceLayout()
        if (view is ViewGroup) {
            for (index in 0..<view.childCount) {
                forceLayoutOfChildren(view.getChildAt(index))
            }
        }
    }
}
