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

public val ProductIcons.Miscellaneous: ImageVector
    get() {
        if (_miscellaneous != null) {
            return _miscellaneous!!
        }
        _miscellaneous = Builder(name = "Miscellaneous", defaultWidth = 100.0.dp, defaultHeight =
                100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(50.274f, 14.254f)
                lineToRelative(31.431f, 12.545f)
                curveToRelative(0.771f, 0.313f, 1.302f, 1.109f, 1.295f, 1.935f)
                verticalLineToRelative(42.345f)
                curveToRelative(-0.006f, 0.819f, -0.535f, 1.596f, -1.298f, 1.905f)
                lineToRelative(-31.427f, 12.545f)
                curveToRelative(-0.493f, 0.198f, -1.058f, 0.198f, -1.55f, 0.001f)
                lineToRelative(-31.43f, -12.547f)
                curveToRelative(-0.761f, -0.308f, -1.289f, -1.085f, -1.295f, -1.907f)
                verticalLineToRelative(-42.337f)
                curveToRelative(-0.007f, -0.831f, 0.523f, -1.627f, 1.298f, -1.941f)
                lineToRelative(31.465f, -12.557f)
                curveToRelative(0.213f, -0.066f, 0.435f, -0.109f, 0.663f, -0.12f)
                curveToRelative(0.293f, -0.014f, 0.574f, 0.025f, 0.848f, 0.133f)
                close()
                moveTo(78.855f, 31.775f)
                lineToRelative(-11.568f, 4.623f)
                verticalLineToRelative(11.205f)
                curveToRelative(-0.016f, 1.142f, -0.959f, 2.057f, -2.101f, 2.039f)
                curveToRelative(-1.145f, -0.016f, -2.061f, -0.956f, -2.045f, -2.09f)
                verticalLineToRelative(-9.503f)
                lineToRelative(-11.568f, 4.622f)
                verticalLineToRelative(37.86f)
                lineToRelative(27.282f, -10.873f)
                verticalLineToRelative(-37.882f)
                close()
                moveTo(20.145f, 31.775f)
                verticalLineToRelative(37.883f)
                lineToRelative(27.282f, 10.873f)
                verticalLineToRelative(-37.859f)
                lineToRelative(-27.282f, -10.896f)
                close()
                moveTo(33.787f, 24.691f)
                lineToRelative(-10.118f, 4.043f)
                lineToRelative(25.832f, 10.292f)
                lineToRelative(10.114f, -4.021f)
                lineToRelative(-25.828f, -10.314f)
                close()
                moveTo(49.501f, 18.419f)
                lineToRelative(-10.118f, 4.043f)
                lineToRelative(25.83f, 10.292f)
                lineToRelative(10.116f, -4.021f)
                lineToRelative(-25.828f, -10.314f)
                close()
            }
        }
        .build()
        return _miscellaneous!!
    }

private var _miscellaneous: ImageVector? = null
