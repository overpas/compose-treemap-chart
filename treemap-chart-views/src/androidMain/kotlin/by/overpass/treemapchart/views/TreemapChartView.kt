package by.overpass.treemapchart.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.Tree

/**
 * Treemap chart view that shows a [Tree].
 */
class TreemapChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val groups = mutableListOf<TreemapChartLayout>()

    /**
     * Strategy that measures the nodes of the chart.
     */
    var measurer: TreemapChartMeasurer = SquarifiedMeasurer()
        set(value) {
            field = value
            groups.forEach { it.measurer = value }
        }

    /**
     * Shows the tree with views for the leaf items.
     *
     * @param T type of the node values
     * @param data Items to be displayed
     * @param evaluateItem Function that evaluates an item
     * @param createItemView Creates the view of a leaf treemap item
     */
    fun <T> setData(
        data: Tree<T>,
        evaluateItem: (T) -> Double,
        createItemView: (parent: ViewGroup, item: T) -> View,
    ) {
        setData(
            data = data,
            evaluateItem = evaluateItem,
        ) { parent: ViewGroup, node: Tree.Node<T>, group: TreemapChartLayout? ->
            group ?: createItemView(parent, node.data)
        }
    }

    /**
     * Shows the tree with views for the nodes.
     *
     * @param T type of the node values
     * @param data Items to be displayed
     * @param evaluateItem Function that evaluates an item
     * @param createNodeView Creates the view of a treemap node; For a group, `group` holds the views of
     * the child nodes and the result must contain it; For a leaf, `group` is null
     */
    fun <T> setData(
        data: Tree<T>,
        evaluateItem: (T) -> Double,
        createNodeView: (parent: ViewGroup, node: Tree.Node<T>, group: TreemapChartLayout?) -> View,
    ) {
        removeAllViews()
        val factory = TreemapChartViewFactory(
            context = context,
            measurer = measurer,
            evaluateItem = evaluateItem,
            createNodeView = createNodeView,
        )
        addView(factory.create(this, data.root), LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        groups.clear()
        groups += factory.groups
    }
}
