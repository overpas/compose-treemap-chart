package by.overpass.treemapchart.compose

import androidx.compose.ui.Modifier
import kotlin.test.Test
import kotlin.test.assertIs

class TreemapChartScopeTest {

    @Test
    fun zeroWeightFails() {
        val sut = TreemapChartScopeInstance

        val exception = runCatching {
            with(sut) {
                Modifier.weight(0.0)
            }
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun negativeWeightFails() {
        val sut = TreemapChartScopeInstance

        val exception = runCatching {
            with(sut) {
                Modifier.weight(-1.0)
            }
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }
}
