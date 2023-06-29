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

public val ProductIcons.Metals: ImageVector
    get() {
        if (_metals != null) {
            return _metals!!
        }
        _metals = Builder(name = "Metals", defaultWidth = 100.0.dp, defaultHeight = 100.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(84.028f, 26.0f)
                curveToRelative(1.09f, 0.0f, 1.972f, 0.899f, 1.972f, 2.006f)
                verticalLineToRelative(21.131f)
                curveToRelative(0.0f, 1.107f, -0.883f, 2.004f, -1.972f, 2.004f)
                horizontalLineToRelative(-5.507f)
                curveToRelative(-7.659f, -0.022f, -14.083f, 5.179f, -15.065f, 12.812f)
                horizontalLineToRelative(7.73f)
                curveToRelative(5.142f, 0.0f, 9.311f, 4.814f, 9.311f, 10.04f)
                curveToRelative(0.0f, 1.108f, -0.883f, 2.006f, -1.974f, 2.006f)
                horizontalLineToRelative(-52.591f)
                curveToRelative(-1.091f, 0.0f, -1.974f, -0.899f, -1.974f, -2.006f)
                curveToRelative(0.0f, -5.226f, 4.169f, -10.04f, 9.311f, -10.04f)
                horizontalLineToRelative(8.41f)
                curveToRelative(-0.962f, -6.254f, -6.29f, -10.353f, -12.606f, -10.326f)
                horizontalLineToRelative(-2.531f)
                curveToRelative(-1.09f, 0.0f, -1.974f, -0.899f, -1.974f, -2.006f)
                verticalLineToRelative(-1.722f)
                horizontalLineToRelative(-0.471f)
                curveToRelative(-10.547f, 0.0f, -19.098f, -9.17f, -19.098f, -19.887f)
                curveToRelative(0.0f, -1.109f, 0.883f, -2.006f, 1.974f, -2.006f)
                horizontalLineToRelative(17.594f)
                lineToRelative(0.001f, -0.506f)
                lineToRelative(0.001f, -0.119f)
                curveToRelative(0.0f, -0.977f, 0.786f, -1.276f, 1.75f, -1.367f)
                lineToRelative(0.223f, -0.014f)
                horizontalLineToRelative(57.485f)
                close()
                moveTo(82.054f, 30.013f)
                horizontalLineToRelative(-53.537f)
                verticalLineToRelative(0.497f)
                lineToRelative(0.001f, 2.838f)
                curveToRelative(0.0f, 1.051f, -0.902f, 1.861f, -1.974f, 1.861f)
                curveToRelative(-1.071f, 0.0f, -1.972f, -0.81f, -1.972f, -1.861f)
                verticalLineToRelative(-1.33f)
                horizontalLineToRelative(-15.539f)
                curveToRelative(0.98f, 7.632f, 7.405f, 13.89f, 15.067f, 13.868f)
                horizontalLineToRelative(0.472f)
                lineToRelative(0.001f, -5.232f)
                curveToRelative(0.0f, -1.022f, 0.912f, -1.781f, 1.972f, -1.781f)
                curveToRelative(1.062f, 0.0f, 1.974f, 0.759f, 1.974f, 1.781f)
                verticalLineToRelative(8.96f)
                horizontalLineToRelative(0.619f)
                curveToRelative(9.196f, 0.0f, 16.651f, 7.0f, 16.651f, 16.345f)
                curveToRelative(0.0f, 1.109f, -0.882f, 2.006f, -1.972f, 2.006f)
                horizontalLineToRelative(-10.545f)
                curveToRelative(-2.231f, 0.0f, -4.192f, 1.971f, -4.988f, 4.021f)
                horizontalLineToRelative(47.893f)
                curveToRelative(-0.796f, -2.049f, -2.758f, -4.021f, -4.988f, -4.021f)
                horizontalLineToRelative(-9.786f)
                curveToRelative(-1.09f, 0.0f, -1.972f, -0.898f, -1.972f, -2.006f)
                curveToRelative(0.0f, -10.718f, 8.548f, -18.829f, 19.096f, -18.829f)
                horizontalLineToRelative(3.53f)
                verticalLineToRelative(-17.118f)
                close()
            }
        }
        .build()
        return _metals!!
    }

private var _metals: ImageVector? = null
