package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTimedValue

class SquarifiedMeasurerTest {

    private val squarifiedMeasurer = SquarifiedMeasurer()
    private val values = listOf(6.0, 6.0, 4.0, 3.0, 2.0, 2.0, 1.0)

    @Test
    fun nodesAreMeasuredCorrectlyWithSquarifiedAlgorithmVerticalScreen() {
        val expectedNodes = listOf(
            TreemapNode(width = 540, height = 960, offsetX = 0, offsetY = 0),
            TreemapNode(width = 540, height = 960, offsetX = 540, offsetY = 0),
            TreemapNode(width = 630, height = 548, offsetX = 0, offsetY = 960),
            TreemapNode(width = 630, height = 411, offsetX = 0, offsetY = 1508),
            TreemapNode(width = 450, height = 384, offsetX = 630, offsetY = 960),
            TreemapNode(width = 450, height = 384, offsetX = 630, offsetY = 1344),
            TreemapNode(width = 450, height = 192, offsetX = 630, offsetY = 1728),
        )

        val actual = measureTimedValue {
            squarifiedMeasurer.measureNodes(values, 1080, 1920)
        }

        assertEquals(expectedNodes, actual.value)
        println("Time: ${actual.duration}")
    }

    @Test
    fun nodesAreMeasuredCorrectlyWithSquarifiedAlgorithmHorizontalScreen() {
        val expectedNodes = listOf(
            TreemapNode(width = 960, height = 540, offsetX = 0, offsetY = 0),
            TreemapNode(width = 960, height = 540, offsetX = 0, offsetY = 540),
            TreemapNode(width = 548, height = 630, offsetX = 960, offsetY = 0),
            TreemapNode(width = 411, height = 630, offsetX = 1508, offsetY = 0),
            TreemapNode(width = 384, height = 450, offsetX = 960, offsetY = 630),
            TreemapNode(width = 384, height = 450, offsetX = 1344, offsetY = 630),
            TreemapNode(width = 192, height = 450, offsetX = 1728, offsetY = 630),
        )

        val actual = measureTimedValue {
            squarifiedMeasurer.measureNodes(values, 1920, 1080)
        }

        assertEquals(expectedNodes, actual.value)
        println("Time: ${actual.duration}")
    }
}
