package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
internal fun ProductExportPopup(
    export: Export.Product,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FocusablePopup(
        alignment = Alignment.Center,
        onDismissRequest = onDismiss,
    ) {
        ProductExportCard(
            export = export,
            modifier = modifier
                .width(240.dp)
                .shadow(20.dp, MaterialTheme.shapes.medium),
        )
    }
}

@Composable
private fun ProductExportCard(
    export: Export.Product,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            ProductTitleRow(export)
            Spacer(Modifier.height(12.dp))
            ProductExportsValueRow(export.exportsValue)
            ProductPercentageRow(export.percentage)
        }
    }
}
