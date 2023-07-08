package by.overpass.treemapchart.core.tree

import kotlin.test.Test
import kotlin.test.assertEquals

class TreeTest {

    private val testTree = tree(10) {
        node(6) {
            node(4)
            node(2) {
                node(1)
                node(1)
            }
        }
        node(3) {
            node(2)
            node(1)
        }
        node(1)
    }

    @Test
    fun treeIsCreatedCorrectly() {
        assertEquals(listOf(10, 6, 4, 2, 1, 1, 3, 2, 1, 1), testTree.dfs())
    }
}
