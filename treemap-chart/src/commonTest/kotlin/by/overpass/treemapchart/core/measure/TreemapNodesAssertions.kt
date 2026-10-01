package by.overpass.treemapchart.core.measure

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal val gapProneValues = listOf(
    listOf(6.0, 6.0, 4.0, 3.0, 2.0, 2.0, 1.0),
    listOf(1.0, 1.0, 1.0),
    listOf(7.0, 5.0, 3.0, 3.0, 2.0, 1.0, 1.0, 1.0),
    listOf(13.0, 11.0, 7.0, 5.0, 3.0, 2.0, 1.0),
    List(17) { 1.0 },
)

internal val gapProneSizes = listOf(
    1080 to 1920,
    1920 to 1080,
    1001 to 997,
    333 to 777,
    641 to 359,
)

internal fun assertNodesFillBoundsWithoutGaps(
    nodes: List<TreemapNode>,
    width: Int,
    height: Int,
) {
    nodes.forEach { node ->
        assertTrue(node.offsetX >= 0 && node.offsetY >= 0, "$node starts outside the bounds")
        assertTrue(
            node.offsetX + node.width <= width && node.offsetY + node.height <= height,
            "$node ends outside the bounds $width x $height",
        )
    }
    nodes.forEachIndexed { index, node ->
        nodes.drop(index + 1).forEach { other ->
            assertFalse(node.overlaps(other), "$node overlaps $other")
        }
    }
    assertEquals(
        width.toLong() * height,
        nodes.sumOf { it.width.toLong() * it.height },
        "Nodes do not fill the bounds $width x $height: $nodes",
    )
}

private fun TreemapNode.overlaps(other: TreemapNode): Boolean =
    offsetX < other.offsetX + other.width &&
        other.offsetX < offsetX + width &&
        offsetY < other.offsetY + other.height &&
        other.offsetY < offsetY + height
