package by.overpass.treemapchart.sample.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.sample.shared.complex.ComplexChart
import by.overpass.treemapchart.sample.shared.large.CanvasLargeTreemapChart
import by.overpass.treemapchart.sample.shared.large.LargeChart
import by.overpass.treemapchart.sample.shared.large.LargeTreemapChart
import by.overpass.treemapchart.sample.shared.simple.SimpleChart
import by.overpass.treemapchart.sample.shared.ui.icons.ArrowBack

@Composable
internal fun TreemapChartSample(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    var screen by rememberSaveable { mutableStateOf(SampleScreen.SIMPLE) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TreemapChartSampleTopAppBar(
                screen = screen,
                onBackClick = { screen = SampleScreen.SIMPLE },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        when (screen) {
            SampleScreen.SIMPLE -> {
                SimpleChart(
                    onGoToComplexChartClick = { screen = SampleScreen.COMPLEX },
                    onGoToLargeChartClick = { screen = SampleScreen.LARGE },
                    onGoToCanvasLargeChartClick = { screen = SampleScreen.CANVAS_LARGE },
                    modifier = Modifier.padding(paddingValues),
                )
            }

            SampleScreen.COMPLEX -> {
                ComplexChart(Modifier.padding(paddingValues))
            }

            SampleScreen.LARGE -> {
                LargeChart(Modifier.padding(paddingValues)) { tree, chartModifier ->
                    LargeTreemapChart(tree = tree, modifier = chartModifier)
                }
            }

            SampleScreen.CANVAS_LARGE -> {
                LargeChart(Modifier.padding(paddingValues)) { tree, chartModifier ->
                    CanvasLargeTreemapChart(tree = tree, modifier = chartModifier)
                }
            }
        }
    }
}

@Composable
private fun TreemapChartSampleTopAppBar(
    screen: SampleScreen,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = when (screen) {
                    SampleScreen.SIMPLE -> "Simple chart"
                    SampleScreen.COMPLEX -> "Japan Exports 2021"
                    SampleScreen.LARGE -> "Large chart"
                    SampleScreen.CANVAS_LARGE -> "Large canvas chart"
                },
            )
        },
        navigationIcon = if (screen != SampleScreen.SIMPLE) {
            {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        } else {
            null
        },
        modifier = modifier,
    )
}
