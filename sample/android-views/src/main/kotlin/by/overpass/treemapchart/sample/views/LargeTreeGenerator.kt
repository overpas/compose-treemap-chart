package by.overpass.treemapchart.sample.views

import by.overpass.treemapchart.core.tree.Tree
import by.overpass.treemapchart.core.tree.tree
import kotlin.random.Random

private const val SECTIONS = 10
private const val GROUPS_PER_SECTION = 10
private const val LEAVES_PER_GROUP = 20
private const val MIN_LEAF_VALUE = 1.0
private const val MAX_LEAF_VALUE = 100.0

internal object LargeTreeGenerator {

    fun generate(seed: Int): Tree<Double> {
        val random = Random(seed)
        val sections = List(SECTIONS) {
            List(GROUPS_PER_SECTION) {
                List(LEAVES_PER_GROUP) { random.nextDouble(MIN_LEAF_VALUE, MAX_LEAF_VALUE) }
            }
        }
        return tree(sections.sumOf { groups -> groups.sumOf(List<Double>::sum) }) {
            sections.forEach { groups ->
                node(groups.sumOf(List<Double>::sum)) {
                    groups.forEach { leaves ->
                        node(leaves.sum()) {
                            leaves.forEach(::node)
                        }
                    }
                }
            }
        }
    }
}
