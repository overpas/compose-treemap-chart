package by.overpass.treemapchart.sample.shared.large

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.compose.TreemapChart
import by.overpass.treemapchart.core.tree.Tree

@Composable
internal fun LargeTreemapChart(
    tree: Tree<Double>,
    modifier: Modifier = Modifier,
) {
    LargeTreemapNode(
        node = tree.root,
        modifier = modifier,
    )
}

@Composable
private fun LargeTreemapNode(
    node: Tree.Node<Double>,
    modifier: Modifier = Modifier,
) {
    if (node.children.isEmpty()) {
        Box(modifier.border(0.5.dp, MaterialTheme.colors.onBackground))
    } else {
        TreemapChart(modifier) {
            node.children.forEach { child ->
                LargeTreemapNode(
                    node = child,
                    modifier = Modifier.weight(child.data),
                )
            }
        }
    }
}
