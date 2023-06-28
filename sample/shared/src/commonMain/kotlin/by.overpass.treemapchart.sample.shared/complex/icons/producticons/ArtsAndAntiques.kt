package `by`.overpass.treemapchart.sample.shared.complex.icons.producticons

import `by`.overpass.treemapchart.sample.shared.complex.icons.ProductIcons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val ProductIcons.ArtsAndAntiques: ImageVector
    get() {
        if (_artsandantiques != null) {
            return _artsandantiques!!
        }
        _artsandantiques = Builder(name = "Artsandantiques", defaultWidth = 100.0.dp, defaultHeight
                = 100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(50.0f, 7.25f)
                curveToRelative(1.117f, 0.0f, 2.025f, 0.913f, 2.025f, 2.038f)
                verticalLineToRelative(3.679f)
                horizontalLineToRelative(23.953f)
                curveToRelative(1.116f, 0.0f, 2.023f, 0.914f, 2.023f, 2.04f)
                verticalLineToRelative(8.059f)
                curveToRelative(0.0f, 1.127f, -0.906f, 2.042f, -2.023f, 2.042f)
                horizontalLineToRelative(-1.034f)
                verticalLineToRelative(32.872f)
                horizontalLineToRelative(1.034f)
                curveToRelative(1.116f, 0.0f, 2.023f, 0.914f, 2.023f, 2.04f)
                verticalLineToRelative(7.96f)
                curveToRelative(0.0f, 1.126f, -0.907f, 2.04f, -2.023f, 2.04f)
                horizontalLineToRelative(-7.627f)
                lineToRelative(7.959f, 18.369f)
                curveToRelative(0.481f, 1.133f, -0.037f, 2.23f, -1.071f, 2.671f)
                curveToRelative(-1.031f, 0.439f, -2.176f, 0.057f, -2.65f, -1.064f)
                lineToRelative(-8.642f, -19.977f)
                horizontalLineToRelative(-11.921f)
                verticalLineToRelative(8.396f)
                curveToRelative(0.0f, 1.126f, -0.907f, 2.038f, -2.025f, 2.038f)
                curveToRelative(-1.118f, 0.0f, -2.025f, -0.912f, -2.025f, -2.038f)
                verticalLineToRelative(-8.396f)
                horizontalLineToRelative(-11.921f)
                lineToRelative(-8.642f, 19.977f)
                curveToRelative(-0.478f, 1.119f, -1.623f, 1.501f, -2.652f, 1.063f)
                curveToRelative(-1.034f, -0.44f, -1.55f, -1.537f, -1.068f, -2.672f)
                lineToRelative(7.958f, -18.369f)
                horizontalLineToRelative(-7.625f)
                curveToRelative(-1.118f, 0.0f, -2.025f, -0.914f, -2.025f, -2.04f)
                verticalLineToRelative(-7.96f)
                curveToRelative(0.0f, -1.126f, 0.907f, -2.04f, 2.025f, -2.04f)
                horizontalLineToRelative(1.03f)
                verticalLineToRelative(-32.872f)
                horizontalLineToRelative(-1.03f)
                curveToRelative(-1.118f, 0.0f, -2.025f, -0.914f, -2.025f, -2.042f)
                verticalLineToRelative(-8.059f)
                curveToRelative(0.0f, -1.126f, 0.907f, -2.04f, 2.025f, -2.04f)
                horizontalLineToRelative(23.951f)
                verticalLineToRelative(-3.679f)
                curveToRelative(0.0f, -1.125f, 0.907f, -2.038f, 2.025f, -2.038f)
                close()
                moveTo(73.951f, 62.058f)
                horizontalLineToRelative(-47.904f)
                verticalLineToRelative(3.882f)
                horizontalLineToRelative(47.904f)
                verticalLineToRelative(-3.882f)
                close()
                moveTo(70.896f, 25.106f)
                horizontalLineToRelative(-41.793f)
                verticalLineToRelative(32.874f)
                horizontalLineToRelative(41.793f)
                verticalLineToRelative(-32.874f)
                close()
                moveTo(26.047f, 17.047f)
                verticalLineToRelative(3.981f)
                horizontalLineToRelative(47.904f)
                verticalLineToRelative(-3.981f)
                horizontalLineToRelative(-47.904f)
                close()
            }
        }
        .build()
        return _artsandantiques!!
    }

private var _artsandantiques: ImageVector? = null
