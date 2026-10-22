package by.overpass.treemapchart.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.content.withStyledAttributes
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer

/**
 * Treemap chart layout. Places its children as treemap nodes, each child takes the area that is
 * proportional to its [LayoutParams.weight]. A nested [TreemapChartLayout] is a group.
 */
class TreemapChartLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ViewGroup(context, attrs, defStyleAttr) {

    private val nodes = mutableListOf<TreemapNode>()

    /**
     * Strategy that measures the children of this layout.
     */
    var measurer: TreemapChartMeasurer = SquarifiedMeasurer()
        set(value) {
            field = value
            requestLayout()
        }

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int,
    ) {
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val visibleChildren = visibleChildren()
        nodes.clear()
        nodes += measurer.measureNodes(
            visibleChildren.map { it.weight() },
            (width - paddingLeft - paddingRight).coerceAtLeast(0),
            (height - paddingTop - paddingBottom).coerceAtLeast(0),
        )
        visibleChildren.forEachIndexed { index, child ->
            child.measure(
                MeasureSpec.makeMeasureSpec(nodes[index].width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(nodes[index].height, MeasureSpec.EXACTLY),
            )
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
    ) {
        val isRtl = layoutDirection == LAYOUT_DIRECTION_RTL
        visibleChildren().forEachIndexed { index, child ->
            val node = nodes[index]
            val childLeft = if (isRtl) {
                right - left - paddingRight - node.offsetX - node.width
            } else {
                paddingLeft + node.offsetX
            }
            val childTop = paddingTop + node.offsetY
            child.layout(childLeft, childTop, childLeft + node.width, childTop + node.height)
        }
    }

    override fun shouldDelayChildPressedState(): Boolean =
        false

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean =
        p is LayoutParams

    override fun generateDefaultLayoutParams(): LayoutParams =
        LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams =
        LayoutParams(context, attrs)

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams =
        LayoutParams(p)

    private fun visibleChildren(): List<View> =
        (0..<childCount)
            .map(::getChildAt)
            .filter { it.visibility != GONE }

    private fun View.weight(): Double {
        val params = layoutParams
        check(params is LayoutParams) { "Child layout params are not TreemapChartLayout.LayoutParams" }
        return params.weight
    }

    /**
     * Layout parameters of a [TreemapChartLayout] child.
     */
    class LayoutParams : ViewGroup.LayoutParams {

        /**
         * Weight of the child. The child takes the area that is proportional to it. Must be finite and
         * greater than zero.
         */
        var weight: Double = DEFAULT_WEIGHT
            set(value) {
                require(value > 0.0 && value.isFinite()) {
                    "Invalid weight $value; must be finite and greater than zero"
                }
                field = value
            }

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
            context.withStyledAttributes(attrs, R.styleable.TreemapChartLayout_Layout) {
                weight = getFloat(R.styleable.TreemapChartLayout_Layout_layout_treemapWeight, DEFAULT_WEIGHT.toFloat())
                    .toDouble()
            }
        }

        @JvmOverloads
        constructor(
            width: Int,
            height: Int,
            weight: Double = DEFAULT_WEIGHT,
        ) : super(width, height) {
            this.weight = weight
        }

        constructor(source: ViewGroup.LayoutParams) : super(source) {
            if (source is LayoutParams) {
                weight = source.weight
            }
        }

        private companion object {
            const val DEFAULT_WEIGHT = 1.0
        }
    }
}
