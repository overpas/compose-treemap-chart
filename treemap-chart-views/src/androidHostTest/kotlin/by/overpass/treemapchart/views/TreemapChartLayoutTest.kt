package by.overpass.treemapchart.views

import android.content.pm.ApplicationInfo
import android.graphics.Rect
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class TreemapChartLayoutTest {

    private val context = RuntimeEnvironment.getApplication()
    private val sut = TreemapChartLayout(context)

    @Test
    fun childrenArePlacedAtTheMeasurerOffsetsPlusPadding() {
        sut.measurer = SliceAndDiceMeasurer
        sut.setPadding(10, 10, 10, 10)
        val first = View(context)
        val second = View(context)
        val third = View(context)
        sut.addView(first, TreemapChartLayout.LayoutParams(0, 0, 1.0))
        sut.addView(second, TreemapChartLayout.LayoutParams(0, 0, 1.0))
        sut.addView(third, TreemapChartLayout.LayoutParams(0, 0, 2.0))
        val expectedBounds = listOf(Rect(10, 10, 110, 110), Rect(110, 10, 210, 110), Rect(210, 10, 410, 110))

        sut.measure(
            MeasureSpec.makeMeasureSpec(420, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(120, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 420, 120)
        val actualBounds = listOf(first, second, third).map { Rect(it.left, it.top, it.right, it.bottom) }

        assertEquals(expectedBounds, actualBounds)
    }

    @Test
    fun childrenFillTheContentAreaWithTheDefaultMeasurer() {
        val children = listOf(View(context), View(context), View(context), View(context))
        sut.addView(children[0], TreemapChartLayout.LayoutParams(0, 0, 6.0))
        sut.addView(children[1], TreemapChartLayout.LayoutParams(0, 0, 3.0))
        sut.addView(children[2], TreemapChartLayout.LayoutParams(0, 0, 2.0))
        sut.addView(children[3], TreemapChartLayout.LayoutParams(0, 0, 1.0))

        sut.measure(
            MeasureSpec.makeMeasureSpec(301, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(199, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 301, 199)
        val childrenArea = children.sumOf { it.width * it.height }

        assertEquals(301 * 199, childrenArea)
    }

    @Test
    fun goneChildGetsNoSpaceAndTheVisibleChildrenFillTheArea() {
        sut.measurer = SliceAndDiceMeasurer
        val first = View(context)
        val gone = View(context)
        gone.visibility = View.GONE
        val last = View(context)
        sut.addView(first, TreemapChartLayout.LayoutParams(0, 0, 1.0))
        sut.addView(gone, TreemapChartLayout.LayoutParams(0, 0, 5.0))
        sut.addView(last, TreemapChartLayout.LayoutParams(0, 0, 1.0))
        val expectedBounds = listOf(Rect(0, 0, 200, 100), Rect(0, 0, 0, 0), Rect(200, 0, 400, 100))

        sut.measure(
            MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 400, 100)
        val actualBounds = listOf(first, gone, last).map { Rect(it.left, it.top, it.right, it.bottom) }

        assertEquals(expectedBounds, actualBounds)
    }

    @Test
    fun rtlLayoutMirrorsTheChildrenHorizontally() {
        context.applicationInfo.flags = context.applicationInfo.flags or ApplicationInfo.FLAG_SUPPORTS_RTL
        sut.measurer = SliceAndDiceMeasurer
        sut.layoutDirection = View.LAYOUT_DIRECTION_RTL
        sut.setPadding(10, 0, 30, 0)
        val first = View(context)
        val second = View(context)
        sut.addView(first, TreemapChartLayout.LayoutParams(0, 0, 1.0))
        sut.addView(second, TreemapChartLayout.LayoutParams(0, 0, 3.0))
        val expectedBounds = listOf(Rect(310, 0, 410, 100), Rect(10, 0, 310, 100))

        sut.measure(
            MeasureSpec.makeMeasureSpec(440, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 440, 100)
        val actualBounds = listOf(first, second).map { Rect(it.left, it.top, it.right, it.bottom) }

        assertEquals(expectedBounds, actualBounds)
    }

    @Test
    fun childAddedWithPlainLayoutParamsGetsWeightOne() {
        val child = View(context)

        sut.addView(child, ViewGroup.LayoutParams(0, 0))
        val weight = (child.layoutParams as TreemapChartLayout.LayoutParams).weight

        assertEquals(1.0, weight, absoluteTolerance = 0.0001)
    }

    @Test
    fun newMeasurerRequestsANewLayout() {
        sut.measure(
            MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY),
        )
        sut.layout(0, 0, 400, 100)

        sut.measurer = SliceAndDiceMeasurer
        val isLayoutRequested = sut.isLayoutRequested

        assertTrue(isLayoutRequested)
    }

    @Test
    fun unspecifiedHeightGivesZeroHeight() {
        sut.addView(View(context), TreemapChartLayout.LayoutParams(0, 0, 2.0))
        sut.addView(View(context), TreemapChartLayout.LayoutParams(0, 0, 1.0))

        sut.measure(
            MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
        )

        assertEquals(0, sut.measuredHeight)
    }
}
