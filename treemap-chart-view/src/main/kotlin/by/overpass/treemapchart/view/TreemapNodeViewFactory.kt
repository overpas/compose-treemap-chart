package by.overpass.treemapchart.view

import android.view.View
import android.view.ViewGroup
import by.overpass.treemapchart.core.tree.Tree

/**
 * Creates the view of a treemap node (leaf or group).
 *
 * @param T type of the node values
 */
fun interface TreemapNodeViewFactory<T> {

    /**
     * @param parent the layout that contains the view
     * @param node the treemap node
     * @param groupView the layout of the child nodes, or null if the node is a leaf
     * @return the view of the node; It must contain [groupView] to show the child nodes
     */
    fun create(
        parent: ViewGroup,
        node: Tree.Node<T>,
        groupView: View?,
    ): View
}
