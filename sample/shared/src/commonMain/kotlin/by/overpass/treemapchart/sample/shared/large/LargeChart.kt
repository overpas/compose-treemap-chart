package by.overpass.treemapchart.sample.shared.large

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.core.tree.Tree

private const val SHRUNK_CHART_FRACTION = 0.7f

@Composable
internal fun LargeChart(
    modifier: Modifier = Modifier,
    chart: @Composable (tree: Tree<Double>, modifier: Modifier) -> Unit,
) {
    var seed by rememberSaveable { mutableIntStateOf(0) }
    var isShrunk by rememberSaveable { mutableStateOf(false) }
    val tree = remember(seed) { LargeTreeGenerator.generate(seed) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            chart(tree, Modifier.fillMaxSize(if (isShrunk) SHRUNK_CHART_FRACTION else 1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { seed++ }) {
                Text(text = "Change data")
            }
            Button(onClick = { isShrunk = !isShrunk }) {
                Text(text = "Resize")
            }
        }
    }
}
