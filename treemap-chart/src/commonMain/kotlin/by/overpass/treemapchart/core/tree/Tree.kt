package by.overpass.treemapchart.core.tree

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

/**
 * Basic data structure to be used in treemap chart.
 *
 * @param T type of the node values
 * @property root the root node
 */
@Stable
data class Tree<T>(val root: Node<T>) {

    /**
     * Represents a tree node.
     *
     * @param T type of the node value
     * @property data value of the node
     * @property children child nodes of the node
     */
    @Stable
    data class Node<T>(
        val data: T,
        val children: ImmutableList<Node<T>>,
    )
}

fun <T> Tree<T>.dfs(): List<T> {
    val list = mutableListOf<T>()
    fun dfs(node: Tree.Node<T>) {
        list += node.data
        node.children.forEach {
            dfs(it)
        }
    }
    dfs(root)
    return list
}
