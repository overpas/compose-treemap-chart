package by.overpass.treemapchart.core.tree

@Suppress("ForbiddenComment")
/**
 * Basic data structure to be used in treemap chart
 * TODO: Fix stability
 *
 * @param root the root node
 */
class Tree<T>(
    val root: Node<T>,
) {

    /**
     * Represents a tree node
     *
     * @param data value of the node
     * @param children child nodes of the node
     */
    class Node<T>(val data: T, children: List<Node<T>> = listOf()) {

        private val internalChildren = children.toMutableList()
        val children: List<Node<T>> get() = internalChildren


        fun addChild(node: Node<T>) {
            internalChildren += node
        }

        fun removeChild(node: Node<T>) {
            internalChildren -= node
        }
    }
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
