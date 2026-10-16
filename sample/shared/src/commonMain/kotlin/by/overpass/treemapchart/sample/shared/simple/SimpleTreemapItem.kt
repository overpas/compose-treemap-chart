package by.overpass.treemapchart.sample.shared.simple

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun SimpleTreemapItem(
    item: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.border(1.dp, MaterialTheme.colors.onBackground),
    ) {
        Text(
            text = item,
            textAlign = TextAlign.Center,
        )
    }
}
