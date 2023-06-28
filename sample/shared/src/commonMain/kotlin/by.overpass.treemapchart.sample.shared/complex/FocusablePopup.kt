package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.IntOffset

@Composable
internal expect fun FocusablePopup(
    alignment: Alignment = Alignment.TopStart,
    offset: IntOffset = IntOffset(0, 0),
    onDismissRequest: (() -> Unit)? = null,
    onPreviewKeyEvent: ((KeyEvent) -> Boolean) = { false },
    onKeyEvent: ((KeyEvent) -> Boolean) = { false },
    content: @Composable () -> Unit,
)