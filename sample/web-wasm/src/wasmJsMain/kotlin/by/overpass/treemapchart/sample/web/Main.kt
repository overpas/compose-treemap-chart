package by.overpass.treemapchart.sample.web

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import by.overpass.treemapchart.sample.shared.App


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("TreemapChart Compose Web Sample") {
        App(Modifier.fillMaxSize())
    }
}
