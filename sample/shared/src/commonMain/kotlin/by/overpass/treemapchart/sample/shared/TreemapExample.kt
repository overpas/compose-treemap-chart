package by.overpass.treemapchart.sample.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.sample.shared.complex.ComplexChart
import by.overpass.treemapchart.sample.shared.simple.SimpleChart

@Composable
internal fun TreemapChartSample(
    modifier: Modifier = Modifier,
) {
    val scaffoldState = rememberScaffoldState()
    var showComplexChart by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TreemapChartSampleTopAppBar(showComplexChart = showComplexChart) {
                showComplexChart = false
            }
        },
        modifier = modifier,
    ) { paddingValues ->
        if (showComplexChart) {
            ComplexChart(Modifier.padding(paddingValues))
        } else {
            SimpleChart(Modifier.padding(paddingValues)) {
                showComplexChart = true
            }
        }
    }
}

@Composable
private fun TreemapChartSampleTopAppBar(
    showComplexChart: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = if (showComplexChart) {
                    "Japan Exports 2021"
                } else {
                    "Simple chart"
                },
            )
        },
        navigationIcon = if (showComplexChart) {
            {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        } else null,
        modifier = modifier,
    )
}
