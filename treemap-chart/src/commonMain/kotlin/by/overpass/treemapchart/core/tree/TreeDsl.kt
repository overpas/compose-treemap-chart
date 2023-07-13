/**
 * DSL for creating the tree structure to be displayed in the treemap chart
 */

package by.overpass.treemapchart.core.tree

@DslMarker
annotation class TreeDslMarker

@TreeDslMarker
interface NodeDsl<in T> {

    @TreeDslMarker
    fun node(value: T, nodeBuilder: NodeDsl<T>.() -> Unit = {})
}

@TreeDslMarker
private class NodeDslImpl<T>(private val node: Tree.Node<T>) : NodeDsl<T> {

    private val childNodes = mutableListOf<NodeDslImpl<T>>()

    override fun node(value: T, nodeBuilder: NodeDsl<T>.() -> Unit) {
        childNodes += NodeDslImpl(Tree.Node(value)).apply(nodeBuilder)
    }

    fun build(): Tree.Node<T> {
        childNodes.forEach {
            node.addChild(it.build())
        }
        return node
    }
}

@TreeDslMarker
interface TreeDsl<in T> : NodeDsl<T>

@TreeDslMarker
private class TreeDslImpl<T>(rootValue: T) : TreeDsl<T> {

    private val rootNode = NodeDslImpl(Tree.Node(rootValue))

    override fun node(value: T, nodeBuilder: NodeDsl<T>.() -> Unit) {
        rootNode.node(value, nodeBuilder)
    }

    fun build(): Tree<T> = Tree(rootNode.build())
}

@TreeDslMarker
fun <T> tree(rootValue: T, treeBuilder: TreeDsl<T>.() -> Unit): Tree<T> = TreeDslImpl(rootValue)
    .apply(treeBuilder)
    .build()
