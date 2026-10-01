package by.overpass.treemapchart.core.tree

@TreeDslMarker
interface TreeDsl<in T> : NodeDsl<T>

@TreeDslMarker
private class TreeDslImpl<T>(private val rootValue: T) : TreeDsl<T> {

    private val rootNode = NodeDslImpl<T>()

    override fun node(
        value: T,
        nodeBuilder: NodeDsl<T>.() -> Unit,
    ) {
        rootNode.node(value, nodeBuilder)
    }

    fun build(): Tree<T> =
        Tree(rootNode.build(rootValue))
}

/**
 * DSL for creating the tree structure to be displayed in the treemap chart.
 *
 * @param T type of the node values
 * @param rootValue value of the root node
 * @param treeBuilder builder of the child nodes
 * @return the built tree
 */
@TreeDslMarker
fun <T> tree(
    rootValue: T,
    treeBuilder: TreeDsl<T>.() -> Unit,
): Tree<T> =
    TreeDslImpl(rootValue)
        .apply(treeBuilder)
        .build()
