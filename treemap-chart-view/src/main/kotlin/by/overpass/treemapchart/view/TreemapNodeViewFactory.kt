package by.overpass.treemapchart.view

import android.view.View
import android.view.ViewGroup
import by.overpass.treemapchart.core.tree.Tree

fun interface TreemapNodeViewFactory<T> {

    fun create(
        parent: ViewGroup,
        node: Tree.Node<T>,
        groupView: View,
    ): View
}
