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

public val ProductIcons.Textiles: ImageVector
    get() {
        if (_textiles != null) {
            return _textiles!!
        }
        _textiles = Builder(name = "Textiles", defaultWidth = 100.0.dp, defaultHeight = 100.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(73.15f, 16.071f)
                curveToRelative(0.52f, 0.028f, 0.979f, 0.222f, 1.336f, 0.565f)
                lineToRelative(0.005f, 0.005f)
                lineToRelative(15.191f, 15.228f)
                curveToRelative(0.337f, 0.355f, 0.477f, 0.803f, 0.491f, 1.368f)
                curveToRelative(0.0f, 0.498f, -0.24f, 0.98f, -0.627f, 1.375f)
                lineToRelative(-11.526f, 11.388f)
                curveToRelative(-0.752f, 0.725f, -2.265f, 0.732f, -2.995f, 0.034f)
                lineToRelative(-2.986f, -2.561f)
                verticalLineToRelative(37.59f)
                curveToRelative(0.0f, 1.871f, -1.489f, 2.938f, -3.25f, 2.938f)
                horizontalLineToRelative(-37.255f)
                curveToRelative(-2.001f, 0.0f, -3.522f, -1.067f, -3.522f, -2.938f)
                verticalLineToRelative(-37.58f)
                lineToRelative(-2.846f, 2.504f)
                curveToRelative(-0.732f, 0.76f, -2.426f, 0.764f, -3.218f, -0.004f)
                lineToRelative(-10.36f, -11.369f)
                curveToRelative(-0.362f, -0.383f, -0.555f, -0.866f, -0.555f, -1.377f)
                curveToRelative(0.0f, -0.503f, 0.196f, -0.996f, 0.564f, -1.382f)
                lineToRelative(16.181f, -15.216f)
                curveToRelative(0.346f, -0.337f, 0.802f, -0.538f, 1.316f, -0.568f)
                lineToRelative(10.798f, -0.07f)
                curveToRelative(0.841f, 0.0f, 1.585f, 0.517f, 1.873f, 1.311f)
                curveToRelative(0.19f, 0.578f, 0.5f, 1.158f, 0.922f, 1.732f)
                curveToRelative(1.708f, 2.235f, 4.748f, 3.583f, 8.133f, 3.583f)
                curveToRelative(3.323f, 0.0f, 6.441f, -1.384f, 8.117f, -3.577f)
                curveToRelative(0.412f, -0.554f, 0.727f, -1.141f, 0.932f, -1.732f)
                lineToRelative(0.054f, -0.145f)
                curveToRelative(0.296f, -0.707f, 1.003f, -1.172f, 1.801f, -1.172f)
                lineToRelative(11.426f, 0.071f)
                close()
                moveTo(62.983f, 19.914f)
                curveToRelative(-0.272f, 0.528f, -0.592f, 1.032f, -0.955f, 1.503f)
                curveToRelative(-2.436f, 3.215f, -6.631f, 5.125f, -11.219f, 5.125f)
                curveToRelative(-4.589f, 0.0f, -8.789f, -1.915f, -11.229f, -5.134f)
                curveToRelative(-0.287f, -0.369f, -0.551f, -0.764f, -0.788f, -1.18f)
                lineToRelative(-0.172f, -0.315f)
                lineToRelative(-8.697f, 0.07f)
                lineToRelative(-14.223f, 13.295f)
                lineToRelative(7.622f, 8.58f)
                lineToRelative(5.241f, -4.388f)
                curveToRelative(0.581f, -0.544f, 1.839f, -1.084f, 2.716f, -0.576f)
                curveToRelative(0.877f, 0.507f, 1.093f, 0.941f, 1.093f, 1.977f)
                verticalLineToRelative(41.13f)
                horizontalLineToRelative(35.307f)
                verticalLineToRelative(-41.126f)
                curveToRelative(0.0f, -0.781f, 0.494f, -1.454f, 1.718f, -1.981f)
                curveToRelative(1.224f, -0.526f, 2.626f, 0.049f, 3.157f, 0.609f)
                lineToRelative(4.344f, 4.343f)
                lineToRelative(8.603f, -8.601f)
                lineToRelative(-13.2f, -13.262f)
                lineToRelative(-9.319f, -0.07f)
                close()
            }
        }
        .build()
        return _textiles!!
    }

private var _textiles: ImageVector? = null
