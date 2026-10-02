package by.overpass.treemapchart.view

/**
 * Evaluates the weight of a treemap item.
 *
 * @param T type of the node values
 */
fun interface TreemapItemEvaluator<in T> {

    /**
     * @param item the value of a node
     * @return the weight of the node
     */
    fun evaluate(item: T): Double
}
