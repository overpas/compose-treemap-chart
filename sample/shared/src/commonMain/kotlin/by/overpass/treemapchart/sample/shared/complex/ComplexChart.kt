package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.core.tree.Tree

private sealed interface ExportsState {

    data class Loaded(val tree: Tree<Export>) : ExportsState

    data object Parsing : ExportsState
}

@Composable
internal fun ComplexChart(modifier: Modifier = Modifier) {
    val exportsState by produceState<ExportsState>(ExportsState.Parsing) {
        value = ExportsState.Loaded(ExportTreeDataProvider.get())
    }
    var productExportItemSelected by remember { mutableStateOf<Export.Product?>(null) }
    Box(modifier.fillMaxSize()) {
        when (val state = exportsState) {
            is ExportsState.Parsing -> {
                ParsingExports(Modifier.fillMaxSize())
            }

            is ExportsState.Loaded -> {
                CountryExportsTreemapChart(
                    tree = state.tree,
                    onItemClick = { productExportItemSelected = it },
                )
            }
        }
        Box(Modifier.fillMaxSize()) {
            productExportItemSelected?.let { productExport ->
                ProductExportPopup(
                    export = productExport,
                    onDismiss = { productExportItemSelected = null },
                )
            }
        }
    }
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
