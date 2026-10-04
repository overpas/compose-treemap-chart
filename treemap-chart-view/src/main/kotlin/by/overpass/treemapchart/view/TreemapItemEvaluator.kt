package by.overpass.treemapchart.view

fun interface TreemapItemEvaluator<T> {

    fun evaluate(item: T): Double
}
