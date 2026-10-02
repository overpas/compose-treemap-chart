package by.overpass.treemapchart.view

import android.view.View
import android.view.ViewGroup

/**
 * Creates the view of a leaf treemap item.
 *
 * @param T type of the node values
 */
fun interface TreemapItemViewFactory<in T> {

    /**
     * @param parent the layout that contains the view
     * @param item the value of the leaf node
     * @return the view of the leaf node
     */
    fun create(
        parent: ViewGroup,
        item: T,
    ): View
}
