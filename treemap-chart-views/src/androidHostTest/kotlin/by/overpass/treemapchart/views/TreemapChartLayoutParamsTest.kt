package by.overpass.treemapchart.views

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.Test
import kotlin.test.assertIs

@RunWith(RobolectricTestRunner::class)
class TreemapChartLayoutParamsTest {

    private val sut = TreemapChartLayout.LayoutParams(0, 0, 1.0)

    @Test
    fun zeroWeightFails() {
        val exception = runCatching {
            sut.weight = 0.0
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun negativeWeightFails() {
        val exception = runCatching {
            sut.weight = -1.0
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun nanWeightFails() {
        val exception = runCatching {
            sut.weight = Double.NaN
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun infiniteWeightFails() {
        val exception = runCatching {
            sut.weight = Double.POSITIVE_INFINITY
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }
}
