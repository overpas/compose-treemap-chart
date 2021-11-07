package by.overpass.treemapchart.core.measure.squarified

import by.overpass.treemapchart.core.measure.TreemapNode
import org.junit.Assert.assertEquals
import org.junit.Test

class SquarifiedMeasurerTest {

    private val squarifiedMeasurer = SquarifiedMeasurer()

    @Test
    fun `nodes are measured according to the squarified algorithm`() {
        val values = listOf(6.0, 6.0, 4.0, 3.0, 2.0, 2.0, 1.0)
        val expectedNodes = listOf(
            TreemapNode(width = 540, height = 960, offsetX = 0, offsetY = 0),
            TreemapNode(width = 540, height = 960, offsetX = 540, offsetY = 0),
            TreemapNode(width = 630, height = 548, offsetX = 0, offsetY = 960),
            TreemapNode(width = 630, height = 411, offsetX = 0, offsetY = 1508),
            TreemapNode(width = 450, height = 384, offsetX = 630, offsetY = 960),
            TreemapNode(width = 450, height = 384, offsetX = 630, offsetY = 1344),
            TreemapNode(width = 450, height = 192, offsetX = 630, offsetY = 1728)
        )
        val nodes = squarifiedMeasurer.measureNodes(values, 1080, 1920)

        assertEquals(expectedNodes, nodes)
    }
}