@file:Suppress("FunctionNaming", "MagicNumber")

package by.overpass.treemapchart.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import by.overpass.treemapchart.example.ui.theme.ComposetreemapchartTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetreemapchartTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TreemapExample(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
