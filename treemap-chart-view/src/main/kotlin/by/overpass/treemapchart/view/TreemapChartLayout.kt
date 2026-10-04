package by.overpass.treemapchart.view

import android.content.Context
import android.view.ViewGroup
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode

internal class TreemapChartLayout(
    context: Context,
    private val values: List<Double>,
    private val measurer: () -> TreemapChartMeasurer,
) : ViewGroup(context) {

    private var nodes: List<TreemapNode> = emptyList()

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int,
    ) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        nodes = if (values.isEmpty()) emptyList() else measurer().measureNodes(values, width, height)
        nodes.forEachIndexed { index, node ->
            getChildAt(index).measure(
                MeasureSpec.makeMeasureSpec(node.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(node.height, MeasureSpec.EXACTLY),
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
        nodes.forEachIndexed { index, node ->
            val childLeft = if (isRtl) right - left - node.offsetX - node.width else node.offsetX
            getChildAt(index).layout(
                childLeft,
                node.offsetY,
                childLeft + node.width,
                node.offsetY + node.height,
            )
        }
    }
}
