package by.overpass.treemapchart.compose

import by.overpass.treemapchart.core.tree.Tree

internal data class CanvasTreemapNode<T>(
    val node: Tree.Node<T>,
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int,
)
