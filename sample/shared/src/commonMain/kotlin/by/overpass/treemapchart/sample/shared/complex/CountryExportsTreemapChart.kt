package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.compose.TreemapChart
import by.overpass.treemapchart.core.tree.Tree

@Composable
internal fun CountryExportsTreemapChart(
    tree: Tree<Export>,
    onItemClick: (Export.Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    ExportNode(
        node = tree.root,
        onItemClick = onItemClick,
        modifier = modifier,
    )
}

@Composable
private fun ExportNode(
    node: Tree.Node<Export>,
    onItemClick: (Export.Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (val export = node.data) {
        is Export.Product -> {
            ProductExportItem(
                item = export,
                onClick = onItemClick,
                modifier = modifier,
            )
        }

        is Export.Section -> {
            SectionExportItem(
                sectionColor = export.color,
                modifier = modifier,
            ) {
                TreemapChart {
                    node.children.forEach { child ->
                        ExportNode(
                            node = child,
                            onItemClick = onItemClick,
                            modifier = Modifier.weight(child.data.exportsValue),
                        )
                    }
                }
            }
        }
    }
}
