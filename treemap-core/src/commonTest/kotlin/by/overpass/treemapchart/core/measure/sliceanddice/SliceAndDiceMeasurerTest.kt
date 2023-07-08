package by.overpass.treemapchart.core.measure.sliceanddice

import by.overpass.treemapchart.core.measure.TreemapNode
import kotlin.test.Test
import kotlin.test.assertEquals

class SliceAndDiceMeasurerTest {

    private val sliceAndDiceMeasurer = SliceAndDiceMeasurer
    private val values = listOf(6.0, 6.0, 4.0, 3.0, 2.0, 2.0, 1.0)

    @Test
    fun nodesAreMeasuredCorrectlyTheSliceAndDiceWayVerticalScreen() {
        val expectedNodes = listOf(
            TreemapNode(width = 1080, height = 480, offsetX = 0, offsetY = 0),
            TreemapNode(width = 1080, height = 480, offsetX = 0, offsetY = 480),
            TreemapNode(width = 1080, height = 320, offsetX = 0, offsetY = 960),
            TreemapNode(width = 1080, height = 240, offsetX = 0, offsetY = 1280),
            TreemapNode(width = 1080, height = 160, offsetX = 0, offsetY = 1520),
            TreemapNode(width = 1080, height = 160, offsetX = 0, offsetY = 1680),
            TreemapNode(width = 1080, height = 80, offsetX = 0, offsetY = 1840),
        )
        val nodes = sliceAndDiceMeasurer.measureNodes(values, 1080, 1920)

        assertEquals(expectedNodes, nodes)
    }

    @Test
    fun nodesAreMeasuredCorrectlyTheSliceAndDiceWayHorizontalScreen() {
        val expectedNodes = listOf(
            TreemapNode(width = 480, height = 1080, offsetX = 0, offsetY = 0),
            TreemapNode(width = 480, height = 1080, offsetX = 480, offsetY = 0),
            TreemapNode(width = 320, height = 1080, offsetX = 960, offsetY = 0),
            TreemapNode(width = 240, height = 1080, offsetX = 1280, offsetY = 0),
            TreemapNode(width = 160, height = 1080, offsetX = 1520, offsetY = 0),
            TreemapNode(width = 160, height = 1080, offsetX = 1680, offsetY = 0),
            TreemapNode(width = 80, height = 1080, offsetX = 1840, offsetY = 0),
        )
        val nodes = sliceAndDiceMeasurer.measureNodes(values, 1920, 1080)

        assertEquals(expectedNodes, nodes)
    }
}
