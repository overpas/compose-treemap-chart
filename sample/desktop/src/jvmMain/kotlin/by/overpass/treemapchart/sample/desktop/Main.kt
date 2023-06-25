package by.overpass.treemapchart.sample.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import by.overpass.treemapchart.sample.shared.App

fun main() = application {
    Window(title = "Treemap Chart Desktop Sample", onCloseRequest = ::exitApplication) {
        App(Modifier.fillMaxSize())
    }
}
