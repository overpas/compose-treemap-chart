package by.overpass.treemapchart.compose

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

/**
 * Scope for the children of [TreemapChart].
 */
@LayoutScopeMarker
@Immutable
interface TreemapChartScope {

    /**
     * Sets the area of the child in proportion to the weights of its siblings.
     *
     * @param weight Weight of the child; Must be finite and greater than zero
     */
    @Stable
    fun Modifier.weight(weight: Double): Modifier
}

internal object TreemapChartScopeInstance : TreemapChartScope {

    override fun Modifier.weight(weight: Double): Modifier {
        require(weight > 0.0 && weight.isFinite()) { "Invalid weight $weight; must be finite and greater than zero" }
        return then(TreemapChartWeightElement(weight))
    }
}
