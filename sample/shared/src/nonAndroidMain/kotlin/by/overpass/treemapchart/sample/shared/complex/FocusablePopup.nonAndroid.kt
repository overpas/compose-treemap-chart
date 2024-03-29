package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup

@Suppress("LongParameterList")
@Composable
internal actual fun FocusablePopup(
    alignment: Alignment,
    offset: IntOffset,
    onDismissRequest: (() -> Unit)?,
    onPreviewKeyEvent: ((KeyEvent) -> Boolean),
    onKeyEvent: ((KeyEvent) -> Boolean),
    content: @Composable () -> Unit,
) {
    Popup(
        alignment = alignment,
        offset = offset,
        focusable = true,
        onDismissRequest = onDismissRequest,
        onPreviewKeyEvent = onPreviewKeyEvent,
        onKeyEvent = onKeyEvent,
        content = content,
    )
}
