package by.overpass.treemapchart.sample.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.sample.shared.ui.theme.TreemapChartComposeSampleTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    TreemapChartComposeSampleTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colors.background,
        ) {
            TreemapChartSample(
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
