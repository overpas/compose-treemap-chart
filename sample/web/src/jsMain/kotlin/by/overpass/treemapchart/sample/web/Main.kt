package by.overpass.treemapchart.sample.web

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import by.overpass.treemapchart.sample.shared.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("TreemapChart Compose Web Sample") {
            App(Modifier.fillMaxSize())
        }
    }
}
