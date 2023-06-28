package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.compose.TreemapChart
import by.overpass.treemapchart.core.tree.Tree

@Composable
internal fun ComplexChart(
    modifier: Modifier = Modifier,
) {
    val exportsState by loadExports()
    var productExportItemSelected by remember { mutableStateOf<Export.Product?>(null) }
    when (val state = exportsState) {
        is ExportsState.Parsing -> {
            ParsingExports(modifier)
        }

        is ExportsState.Loaded -> {
            CountryExportsTreemapChart(state.tree, modifier) {
                productExportItemSelected = it
            }
        }
    }
    Box(modifier.fillMaxSize()) {
        productExportItemSelected?.let { productExport ->
            ProductExportPopup(productExport) {
                productExportItemSelected = null
            }
        }
    }
}

private sealed class ExportsState {

    data class Loaded(
        val tree: Tree<Export>,
    ) : ExportsState()

    object Parsing : ExportsState()
}

@Composable
private fun loadExports(): State<ExportsState> = produceState<ExportsState>(ExportsState.Parsing) {
    value = ExportsState.Loaded(ExportTreeDataProvider.get())
}

@Composable
private fun ParsingExports(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("Parsing Exports data...")
    }
}

@Composable
private fun CountryExportsTreemapChart(
    tree: Tree<Export>,
    modifier: Modifier = Modifier,
    onItemClick: (Export.Product) -> Unit,
) {
    TreemapChart(
        data = tree,
        evaluateItem = Export::exportsValue,
        modifier = modifier,
    ) { node, GroupContent ->
        val export = node.data
        if (node.children.isEmpty() && export is Export.Product) {
            ProductExportItem(item = export, onClick = onItemClick)
        } else if (export is Export.Section) {
            SectionExportItem(export.color) {
                GroupContent(node)
            }
        }
    }
}

@Composable
private fun ProductExportItem(
    item: Export.Product,
    modifier: Modifier = Modifier,
    onClick: (Export.Product) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .border(0.5.dp, Color.White)
            .clickable { onClick(item) }
            .padding(4.dp),
    ) {
        Text(
            text = item.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.overline,
        )
        Text(
            text = item.percentage.formatPercentage(),
            style = MaterialTheme.typography.overline,
        )
    }
}

@Composable
private fun SectionExportItem(
    sectionColor: Color?,
    modifier: Modifier = Modifier,
    Content: @Composable () -> Unit,
) {
    if (sectionColor != null) {
        Box(
            modifier = modifier
                .background(sectionColor)
        ) {
            Content()
        }
    } else {
        Content()
    }
}
