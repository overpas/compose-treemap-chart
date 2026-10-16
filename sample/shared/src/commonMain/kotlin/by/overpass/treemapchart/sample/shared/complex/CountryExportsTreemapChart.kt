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
    TreemapChart(
        data = tree,
        evaluateItem = Export::exportsValue,
        modifier = modifier,
    ) { node, groupContent ->
        val export = node.data
        if (node.children.isEmpty() && export is Export.Product) {
            ProductExportItem(item = export, onClick = onItemClick)
        } else if (export is Export.Section) {
            SectionExportItem(export.color) {
                groupContent(node)
            }
        }
    }
}
