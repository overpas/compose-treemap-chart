package by.overpass.treemapchart.compose

import androidx.compose.ui.unit.IntSize
import by.overpass.treemapchart.core.measure.TreemapChartMeasurer
import by.overpass.treemapchart.core.measure.TreemapNode
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.tree
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class CanvasTreemapLayoutTest {

    @Test
    fun leafRootTakesFullBounds() {
        val sut = SliceAndDiceMeasurer
        val data = tree(1.0) {}
        val expected = listOf(CanvasTreemapNode(node = data.root, left = 0, top = 0, width = 600, height = 100))

        val actual = sut.measureTreemap(
            root = data.root,
            evaluateItem = { it },
            size = IntSize(600, 100),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun nodesAreInPreOrderWithAbsoluteBounds() {
        val sut = SliceAndDiceMeasurer
        val data = tree(0.0) {
            node(4.0) {
                node(1.0)
                node(3.0)
            }
            node(2.0)
        }
        val first = data.root.children[0]
        val second = data.root.children[1]
        val expected = listOf(
            CanvasTreemapNode(node = data.root, left = 0, top = 0, width = 600, height = 100),
            CanvasTreemapNode(node = first, left = 0, top = 0, width = 400, height = 100),
            CanvasTreemapNode(node = first.children[0], left = 0, top = 0, width = 100, height = 100),
            CanvasTreemapNode(node = first.children[1], left = 100, top = 0, width = 300, height = 100),
            CanvasTreemapNode(node = second, left = 400, top = 0, width = 200, height = 100),
        )

        val actual = sut.measureTreemap(
            root = data.root,
            evaluateItem = { it },
            size = IntSize(600, 100),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun mirroredNodesAreFlippedHorizontally() {
        val data = tree(0.0) {
            node(4.0)
            node(2.0)
        }
        val first = data.root.children[0]
        val second = data.root.children[1]
        val sut = listOf(
            CanvasTreemapNode(node = data.root, left = 0, top = 0, width = 600, height = 100),
            CanvasTreemapNode(node = first, left = 0, top = 0, width = 400, height = 100),
            CanvasTreemapNode(node = second, left = 400, top = 0, width = 200, height = 100),
        )
        val expected = listOf(
            CanvasTreemapNode(node = data.root, left = 0, top = 0, width = 600, height = 100),
            CanvasTreemapNode(node = first, left = 200, top = 0, width = 400, height = 100),
            CanvasTreemapNode(node = second, left = 0, top = 0, width = 200, height = 100),
        )

        val actual = sut.mirrored(600)

        assertEquals(expected, actual)
    }

    @Test
    fun leavesFillBounds() {
        val sut = SquarifiedMeasurer()
        val data = tree(0.0) {
            node(6.0) {
                node(4.0)
                node(2.0) {
                    node(1.0)
                    node(1.0)
                }
            }
            node(3.0) {
                node(2.0)
                node(1.0)
            }
            node(1.0)
        }
        val expected = 1001 * 997

        val actual = sut
            .measureTreemap(
                root = data.root,
                evaluateItem = { it },
                size = IntSize(1001, 997),
            )
            .filter { it.node.children.isEmpty() }
            .sumOf { it.width * it.height }

        assertEquals(expected, actual)
    }

    @Test
    fun zeroWeightFails() {
        val sut = SliceAndDiceMeasurer
        val data = tree(0.0) {
            node(1.0)
            node(0.0)
        }

        val exception = runCatching {
            sut.measureTreemap(
                root = data.root,
                evaluateItem = { it },
                size = IntSize(600, 100),
            )
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun negativeWeightFails() {
        val sut = SliceAndDiceMeasurer
        val data = tree(0.0) {
            node(1.0)
            node(-1.0)
        }

        val exception = runCatching {
            sut.measureTreemap(
                root = data.root,
                evaluateItem = { it },
                size = IntSize(600, 100),
            )
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun nanWeightFails() {
        val sut = SliceAndDiceMeasurer
        val data = tree(0.0) {
            node(1.0)
            node(Double.NaN)
        }

        val exception = runCatching {
            sut.measureTreemap(
                root = data.root,
                evaluateItem = { it },
                size = IntSize(600, 100),
            )
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun infiniteWeightFails() {
        val sut = object : TreemapChartMeasurer {
            override fun measureNodes(
                values: List<Double>,
                width: Int,
                height: Int,
            ): List<TreemapNode> =
                values.map { TreemapNode(width = 0, height = 0, offsetX = 0, offsetY = 0) }
        }
        val data = tree(0.0) {
            node(1.0)
            node(Double.POSITIVE_INFINITY)
        }

        val exception = runCatching {
            sut.measureTreemap(
                root = data.root,
                evaluateItem = { it },
                size = IntSize(600, 100),
            )
        }.exceptionOrNull()

        assertIs<IllegalArgumentException>(exception)
    }

    @Test
    fun nestedChartOfZeroHeightGetsChildrenOfZeroSize() {
        val sut = SquarifiedMeasurer()
        val data = tree(0.0) {
            node(1.0) {
                node(1.0)
                node(1.0)
            }
            node(1000.0)
        }
        val nested = data.root.children[0]
        val expected = listOf(
            CanvasTreemapNode(node = nested.children[0], left = 0, top = 0, width = 0, height = 0),
            CanvasTreemapNode(node = nested.children[1], left = 0, top = 0, width = 0, height = 0),
        )

        val actual = sut
            .measureTreemap(
                root = data.root,
                evaluateItem = { it },
                size = IntSize(100, 100),
            )
            .filter { it.node in nested.children }

        assertEquals(expected, actual)
    }
}
