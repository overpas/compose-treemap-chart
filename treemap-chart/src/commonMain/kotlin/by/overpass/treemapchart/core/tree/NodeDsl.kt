package by.overpass.treemapchart.core.tree

import kotlinx.collections.immutable.toImmutableList

@TreeDslMarker
interface NodeDsl<in T> {

    fun node(
        value: T,
        nodeBuilder: NodeDsl<T>.() -> Unit = {},
    )
}

@TreeDslMarker
internal class NodeDslImpl<T> : NodeDsl<T> {

    private val nodes = mutableListOf<Tree.Node<T>>()

    override fun node(
        value: T,
        nodeBuilder: NodeDsl<T>.() -> Unit,
    ) {
        nodes += NodeDslImpl<T>()
            .apply(nodeBuilder)
            .build(value)
    }

    fun build(value: T): Tree.Node<T> =
        Tree.Node(value, nodes.toImmutableList())
}
