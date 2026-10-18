package by.overpass.treemapchart.sample.shared.large

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.compose.CanvasTreemapChart
import by.overpass.treemapchart.core.tree.Tree

@Composable
internal fun CanvasLargeTreemapChart(
    tree: Tree<Double>,
    modifier: Modifier = Modifier,
) {
    val borderColor = MaterialTheme.colors.onBackground
    CanvasTreemapChart(
        data = tree,
        evaluateItem = { it },
        modifier = modifier.graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen),
    ) { node ->
        if (node.children.isEmpty()) {
            val borderWidth = 0.5.dp.toPx()
            drawRect(
                color = borderColor,
                topLeft = Offset(borderWidth / 2, borderWidth / 2),
                size = Size(size.width - borderWidth, size.height - borderWidth),
                style = Stroke(borderWidth),
            )
        }
    }
}
