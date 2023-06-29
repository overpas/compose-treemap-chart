package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
internal sealed class Export {

    abstract val name: String

    abstract val exportsValue: Double

    abstract val percentage: Double

    @Stable
    data class Product(
        override val name: String,
        override val exportsValue: Double,
        override val percentage: Double,
        val sectionName: String,
        val icon: ImageVector,
        val color: Color,
    ) : Export()

    @Stable
    data class Section(
        override val name: String,
        override val exportsValue: Double,
        override val percentage: Double,
        val color: Color?,
    ) : Export()
}
