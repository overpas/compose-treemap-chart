package by.overpass.treemapchart.core.tree

class Tree<T>(
    val root: Node<T>,
) {
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