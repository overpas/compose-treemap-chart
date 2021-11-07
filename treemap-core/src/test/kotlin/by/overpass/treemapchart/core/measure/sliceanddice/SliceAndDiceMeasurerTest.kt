package by.overpass.treemapchart.core.measure.sliceanddice

import by.overpass.treemapchart.core.measure.TreemapNode
import org.junit.Assert.assertEquals
import org.junit.Test

class SliceAndDiceMeasurerTest {

    private val sliceAndDiceMeasurer = SliceAndDiceMeasurer()

    @Test
    fun `nodes are measured correctly the slice-and-dice way`() {
        val values = listOf(6.0, 6.0, 4.0, 3.0, 2.0, 2.0, 1.0)
        val expectedNodes = listOf(
            TreemapNode(width = 1080, height = 480, offsetX = 0, offsetY = 0),
            TreemapNode(width = 1080, height = 480, offsetX = 0, offsetY = 480),
            TreemapNode(width = 1080, height = 320, offsetX = 0, offsetY = 960),
            TreemapNode(width = 1080, height = 240, offsetX = 0, offsetY = 1280),
            TreemapNode(width = 1080, height = 160, offsetX = 0, offsetY = 1520),
            TreemapNode(width = 1080, height = 160, offsetX = 0, offsetY = 1680),
            TreemapNode(width = 1080, height = 80, offsetX = 0, offsetY = 1840)
        )
        val nodes = sliceAndDiceMeasurer.measureNodes(values, 1080, 1920)

        assertEquals(expectedNodes, nodes)
    }
}