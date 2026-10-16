package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@Composable
internal fun ProductExportItem(
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
internal fun SectionExportItem(
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
