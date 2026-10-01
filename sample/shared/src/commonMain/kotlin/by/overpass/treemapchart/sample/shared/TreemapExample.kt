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
import by.overpass.treemapchart.sample.shared.simple.SimpleChart
import by.overpass.treemapchart.sample.shared.ui.icons.ArrowBack

@Composable
internal fun TreemapChartSample(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    var isComplexChartShown by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TreemapChartSampleTopAppBar(
                isComplexChartShown = isComplexChartShown,
                onBackClick = { isComplexChartShown = false },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        if (isComplexChartShown) {
            ComplexChart(Modifier.padding(paddingValues))
        } else {
            SimpleChart(
                onGoToComplexChartClick = { isComplexChartShown = true },
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}

@Composable
private fun TreemapChartSampleTopAppBar(
    isComplexChartShown: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = if (isComplexChartShown) {
                    "Japan Exports 2021"
                } else {
                    "Simple chart"
                },
            )
        },
        navigationIcon = if (isComplexChartShown) {
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
