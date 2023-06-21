package by.overpass.treemapchart.example

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
internal fun TreemapExample(
    modifier: Modifier = Modifier,
) {
    var showComplexChart by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TreemapExampleTopAppBar(showComplexChart = showComplexChart) {
                showComplexChart = false
            }
        },
        modifier = modifier,
    ) { paddingValues ->
        if (showComplexChart) {
            ComplexChart(modifier.padding(paddingValues)) {
                showComplexChart = false
            }
        } else {
            EasyChart(modifier.padding(paddingValues)) {
                showComplexChart = true
            }
        }
    }
}

@Composable
private fun TreemapExampleTopAppBar(
    showComplexChart: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = if (showComplexChart) {
                    "Complex chart"
                } else {
                    "Easy chart"
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
