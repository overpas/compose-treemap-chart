package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import by.overpass.treemapchart.compose.TreemapChart
import by.overpass.treemapchart.core.tree.Tree

private sealed interface ExportsState {

    data class Loaded(val tree: Tree<Export>) : ExportsState

    data object Parsing : ExportsState
}

@Composable
internal fun ComplexChart(modifier: Modifier = Modifier) {
    val exportsState by loadExports()
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
private fun loadExports(): State<ExportsState> =
    produceState<ExportsState>(ExportsState.Parsing) {
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

@Composable
private fun ProductExportItem(
    item: Export.Product,
    onClick: (Export.Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(0.5.dp, Color.White)
            .clickable { onClick(item) }
            .padding(4.dp),
    ) {
        ShrinkableHidableText(
            text = "${item.name}\n${item.percentage.formatPercentage()}",
            minSize = 6.sp,
        )
    }
}

@Suppress("LongParameterList")
@Composable
private fun ShrinkableHidableText(
    text: String,
    minSize: TextUnit,
    modifier: Modifier = Modifier,
    shrinkSizeFactor: Float = 0.9F,
    textAlign: TextAlign = TextAlign.Center,
    style: TextStyle = MaterialTheme.typography.body1,
) {
    var fontStyle by remember { mutableStateOf(style) }
    var isFitting by remember { mutableStateOf(false) }
    val isVisible by remember { derivedStateOf { fontStyle.fontSize >= minSize } }
    if (isVisible) {
        Text(
            text = text,
            modifier = modifier.drawWithContent {
                if (isFitting) {
                    drawContent()
                }
            },
            textAlign = textAlign,
            onTextLayout = { result ->
                if (result.hasVisualOverflow) {
                    fontStyle = fontStyle.copy(
                        fontSize = fontStyle.fontSize * shrinkSizeFactor,
                        letterSpacing = if (fontStyle.letterSpacing.isUnspecified) {
                            fontStyle.letterSpacing
                        } else {
                            fontStyle.letterSpacing * shrinkSizeFactor
                        },
                    )
                } else {
                    isFitting = true
                }
            },
            style = fontStyle,
        )
    }
}

@Composable
private fun SectionExportItem(
    sectionColor: Color?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.then(sectionColor?.let { Modifier.background(it) } ?: Modifier),
        propagateMinConstraints = true,
    ) {
        content()
    }
}
