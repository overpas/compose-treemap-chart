package by.overpass.treemapchart.view

import android.view.View
import android.view.ViewGroup

fun interface TreemapItemViewFactory<T> {

    fun create(
        parent: ViewGroup,
        item: T,
    ): View
}
