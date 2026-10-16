package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode
import by.overpass.treemapchart.core.measure.assertNodesFillBoundsWithoutGaps
import by.overpass.treemapchart.core.measure.gapProneSizes
import by.overpass.treemapchart.core.measure.gapProneValues
import kotlin.test.Test
import kotlin.test.assertEquals

class SquarifiedMeasurerTest {

    private val sut = SquarifiedMeasurer()
    private val values = listOf(6.0, 6.0, 4.0, 3.0, 2.0, 2.0, 1.0)

    @Test
    fun nodesAreMeasuredCorrectlyWithSquarifiedAlgorithmVerticalScreen() {
        val expectedNodes = listOf(
            TreemapNode(width = 540, height = 960, offsetX = 0, offsetY = 0),
            TreemapNode(width = 540, height = 960, offsetX = 540, offsetY = 0),
            TreemapNode(width = 630, height = 549, offsetX = 0, offsetY = 960),
            TreemapNode(width = 630, height = 411, offsetX = 0, offsetY = 1509),
            TreemapNode(width = 450, height = 384, offsetX = 630, offsetY = 960),
            TreemapNode(width = 450, height = 384, offsetX = 630, offsetY = 1344),
            TreemapNode(width = 450, height = 192, offsetX = 630, offsetY = 1728),
        )

        val actual = sut.measureNodes(values, 1080, 1920)

        assertEquals(expectedNodes, actual)
    }

    @Test
    fun nodesAreMeasuredCorrectlyWithSquarifiedAlgorithmHorizontalScreen() {
        val expectedNodes = listOf(
            TreemapNode(width = 960, height = 540, offsetX = 0, offsetY = 0),
            TreemapNode(width = 960, height = 540, offsetX = 0, offsetY = 540),
            TreemapNode(width = 549, height = 630, offsetX = 960, offsetY = 0),
            TreemapNode(width = 411, height = 630, offsetX = 1509, offsetY = 0),
            TreemapNode(width = 384, height = 450, offsetX = 960, offsetY = 630),
            TreemapNode(width = 384, height = 450, offsetX = 1344, offsetY = 630),
            TreemapNode(width = 192, height = 450, offsetX = 1728, offsetY = 630),
        )

        val actual = sut.measureNodes(values, 1920, 1080)

        assertEquals(expectedNodes, actual)
    }

    @Test
    fun unsortedValuesAreMeasuredInTheirOrder() {
        val values = listOf(1.0, 6.0, 2.0, 4.0, 6.0, 3.0, 2.0)
        val expectedNodes = listOf(
            TreemapNode(width = 175, height = 57, offsetX = 0, offsetY = 0),
            TreemapNode(width = 175, height = 343, offsetX = 0, offsetY = 57),
            TreemapNode(width = 150, height = 133, offsetX = 175, offsetY = 0),
            TreemapNode(width = 150, height = 267, offsetX = 175, offsetY = 133),
            TreemapNode(width = 275, height = 218, offsetX = 325, offsetY = 0),
            TreemapNode(width = 165, height = 182, offsetX = 325, offsetY = 218),
            TreemapNode(width = 110, height = 182, offsetX = 490, offsetY = 218),
        )

        val actual = sut.measureNodes(values, 600, 400)

        assertEquals(expectedNodes, actual)
    }

    @Test
    fun equalValuesAreMeasuredAsAGridInASquare() {
        val values = List(9) { 1.0 }
        val expectedNodes = listOf(
            TreemapNode(width = 100, height = 100, offsetX = 0, offsetY = 0),
            TreemapNode(width = 100, height = 100, offsetX = 100, offsetY = 0),
            TreemapNode(width = 100, height = 100, offsetX = 200, offsetY = 0),
            TreemapNode(width = 100, height = 100, offsetX = 0, offsetY = 100),
            TreemapNode(width = 100, height = 100, offsetX = 0, offsetY = 200),
            TreemapNode(width = 100, height = 100, offsetX = 100, offsetY = 100),
            TreemapNode(width = 100, height = 100, offsetX = 100, offsetY = 200),
            TreemapNode(width = 100, height = 100, offsetX = 200, offsetY = 100),
            TreemapNode(width = 100, height = 100, offsetX = 200, offsetY = 200),
        )

        val actual = sut.measureNodes(values, 300, 300)

        assertEquals(expectedNodes, actual)
    }

    @Test
    fun nodesAreMeasuredCorrectlyWithOddSize() {
        val values = listOf(13.0, 11.0, 7.0, 5.0, 3.0, 2.0, 1.0)
        val expectedNodes = listOf(
            TreemapNode(width = 198, height = 359, offsetX = 0, offsetY = 0),
            TreemapNode(width = 275, height = 219, offsetX = 198, offsetY = 0),
            TreemapNode(width = 275, height = 140, offsetX = 198, offsetY = 219),
            TreemapNode(width = 168, height = 163, offsetX = 473, offsetY = 0),
            TreemapNode(width = 168, height = 98, offsetX = 473, offsetY = 163),
            TreemapNode(width = 112, height = 98, offsetX = 473, offsetY = 261),
            TreemapNode(width = 56, height = 98, offsetX = 585, offsetY = 261),
        )

        val actual = sut.measureNodes(values, 641, 359)

        assertEquals(expectedNodes, actual)
    }

    @Test
    fun nodesFillBoundsWithoutGaps() {
        gapProneValues.forEach { values ->
            gapProneSizes.forEach { (width, height) ->
                val nodes = sut.measureNodes(values, width, height)

                assertNodesFillBoundsWithoutGaps(nodes, width, height)
            }
        }
    }

    @Test
    fun tenThousandValuesFillTheBounds() {
        val values = List(10_000) { index -> (10_000 - index).toDouble() }

        val nodes = sut.measureNodes(values, 1080, 1920)
        val coveredArea = nodes.sumOf { it.width.toLong() * it.height }

        assertEquals(1080L * 1920, coveredArea)
    }
}
