package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun ProductExportPopup(
    export: Export.Product,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
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
private fun ProductExportCard(export: Export.Product, modifier: Modifier = Modifier) {
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

@Composable
private fun ProductTitleRow(export: Export.Product, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            imageVector = export.icon,
            contentDescription = export.name,
            modifier = Modifier
                .size(36.dp)
                .background(export.color, shape = CircleShape),
        )
        Spacer(Modifier.width(12.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = export.name,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = export.sectionName,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@Composable
private fun ProductExportsValueRow(exportsValue: Double, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Exports value",
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = exportsValue.formatTradeValue(),
        )
    }
}

@Composable
private fun ProductPercentageRow(percentage: Double, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Percentage",
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = percentage.formatPercentage(),
        )
    }
}
