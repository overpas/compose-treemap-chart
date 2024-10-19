/**
 * DSL for creating the tree structure to be displayed in the treemap chart
 */

package by.overpass.treemapchart.core.tree

import kotlinx.collections.immutable.toImmutableList

@DslMarker
annotation class TreeDslMarker

@TreeDslMarker
interface NodeDsl<in T> {

    @TreeDslMarker
    fun node(value: T, nodeBuilder: NodeDsl<T>.() -> Unit = {})
}

@TreeDslMarker
private class NodeDslImpl<T> : NodeDsl<T> {

    private val nodes = mutableListOf<Tree.Node<T>>()

    override fun node(value: T, nodeBuilder: NodeDsl<T>.() -> Unit) {
        nodes += NodeDslImpl<T>()
            .apply(nodeBuilder)
            .build(value)
    }

    fun build(value: T): Tree.Node<T> {
        return Tree.Node(value, nodes.toImmutableList())
    }
}

@TreeDslMarker
interface TreeDsl<in T> : NodeDsl<T>

@TreeDslMarker
private class TreeDslImpl<T>(
    private val rootValue: T,
) : TreeDsl<T> {

    private val rootNode = NodeDslImpl<T>()

    override fun node(value: T, nodeBuilder: NodeDsl<T>.() -> Unit) {
        rootNode.node(value, nodeBuilder)
    }

    fun build(): Tree<T> = Tree(rootNode.build(rootValue))
}

@TreeDslMarker
fun <T> tree(rootValue: T, treeBuilder: TreeDsl<T>.() -> Unit): Tree<T> = TreeDslImpl(rootValue)
    .apply(treeBuilder)
    .build()
