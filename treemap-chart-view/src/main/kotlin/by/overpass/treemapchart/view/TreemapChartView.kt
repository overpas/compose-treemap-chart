package by.overpass.treemapchart.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.Tree

/**
 * Treemap chart for the Android View system.
 */
class TreemapChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private var content: (() -> Unit)? = null

    /**
     * The strategy used to measure the treemap nodes.
     */
    var measurer: TreemapChartMeasurer = SquarifiedMeasurer()
        set(value) {
            field = value
            content?.invoke()
        }

    /**
     * Shows the treemap with a view for each leaf item.
     *
     * @param T type of the node values
     * @param data Items to be displayed
     * @param evaluator Function that evaluates an item
     * @param itemViewFactory Creates the view of a leaf treemap item
     */
    fun <T> setData(
        data: Tree<T>,
        evaluator: TreemapItemEvaluator<T>,
        itemViewFactory: TreemapItemViewFactory<T>,
    ) {
        setNodeData(data, evaluator) { parent, node, groupView ->
            groupView ?: itemViewFactory.create(parent, node.data)
        }
    }

    /**
     * Shows the treemap with a view for each node (leaf or group).
     *
     * @param T type of the node values
     * @param data Items to be displayed
     * @param evaluator Function that evaluates an item
     * @param nodeViewFactory Creates the view of a treemap node
     */
    fun <T> setNodeData(
        data: Tree<T>,
        evaluator: TreemapItemEvaluator<T>,
        nodeViewFactory: TreemapNodeViewFactory<T>,
    ) {
        val showContent = {
            removeAllViews()
            addView(TreemapNodeViewBuilder(measurer, evaluator, nodeViewFactory).build(this, data.root))
        }
        content = showContent
        showContent()
    }
}
