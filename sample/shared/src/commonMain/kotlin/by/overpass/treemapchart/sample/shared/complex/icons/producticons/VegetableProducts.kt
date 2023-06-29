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

public val ProductIcons.VegetableProducts: ImageVector
    get() {
        if (_vegetableproducts != null) {
            return _vegetableproducts!!
        }
        _vegetableproducts = Builder(name = "Vegetableproducts", defaultWidth = 100.0.dp,
                defaultHeight = 100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(44.614f, 15.499f)
                lineToRelative(0.153f, 0.077f)
                lineToRelative(6.15f, 3.421f)
                lineToRelative(6.273f, -3.19f)
                curveToRelative(0.67f, -0.337f, 1.474f, -0.262f, 2.066f, 0.198f)
                curveToRelative(0.594f, 0.459f, 0.884f, 1.213f, 0.747f, 1.944f)
                lineToRelative(-0.469f, 2.484f)
                curveToRelative(14.199f, 3.845f, 23.967f, 15.844f, 23.967f, 29.564f)
                curveToRelative(0.0f, 17.018f, -15.03f, 30.839f, -33.5f, 30.839f)
                curveToRelative(-18.469f, 0.0f, -33.5f, -13.822f, -33.5f, -30.839f)
                curveToRelative(0.0f, -14.31f, 10.71f, -26.732f, 25.743f, -30.012f)
                lineToRelative(-0.359f, -2.38f)
                curveToRelative(-0.11f, -0.74f, 0.206f, -1.48f, 0.815f, -1.914f)
                curveToRelative(0.564f, -0.401f, 1.292f, -0.469f, 1.914f, -0.19f)
                close()
                moveTo(42.945f, 24.632f)
                lineToRelative(-0.11f, -0.728f)
                curveToRelative(-13.119f, 2.969f, -22.412f, 13.742f, -22.412f, 26.092f)
                curveToRelative(0.0f, 14.814f, 13.263f, 26.881f, 29.578f, 26.881f)
                curveToRelative(16.315f, 0.0f, 29.58f, -12.067f, 29.58f, -26.881f)
                curveToRelative(0.0f, -11.675f, -8.253f, -21.961f, -20.374f, -25.553f)
                lineToRelative(-0.405f, -0.117f)
                lineToRelative(-0.11f, 0.583f)
                lineToRelative(4.937f, 5.038f)
                curveToRelative(0.53f, 0.535f, 0.709f, 1.333f, 0.46f, 2.047f)
                curveToRelative(-0.232f, 0.658f, -0.782f, 1.14f, -1.45f, 1.285f)
                lineToRelative(-0.156f, 0.027f)
                lineToRelative(-6.96f, 0.893f)
                lineToRelative(-3.23f, 6.312f)
                curveToRelative(-0.334f, 0.594f, -0.934f, 0.988f, -1.595f, 1.045f)
                lineToRelative(-0.166f, 0.007f)
                horizontalLineToRelative(-0.041f)
                curveToRelative(-0.688f, -0.014f, -1.314f, -0.395f, -1.659f, -0.994f)
                lineToRelative(-0.075f, -0.142f)
                lineToRelative(-2.981f, -6.407f)
                lineToRelative(-6.93f, -1.147f)
                curveToRelative(-0.737f, -0.129f, -1.343f, -0.662f, -1.549f, -1.369f)
                curveToRelative(-0.208f, -0.667f, -0.042f, -1.391f, 0.425f, -1.903f)
                lineToRelative(0.113f, -0.114f)
                lineToRelative(5.113f, -4.855f)
                lineToRelative(-0.11f, -0.728f)
                lineToRelative(0.11f, 0.728f)
                close()
                moveTo(46.36f, 20.987f)
                lineToRelative(0.615f, 4.067f)
                curveToRelative(0.098f, 0.648f, -0.126f, 1.307f, -0.595f, 1.737f)
                lineToRelative(-2.976f, 2.814f)
                lineToRelative(4.02f, 0.67f)
                curveToRelative(0.639f, 0.111f, 1.179f, 0.519f, 1.454f, 1.106f)
                lineToRelative(1.734f, 3.723f)
                lineToRelative(1.876f, -3.657f)
                curveToRelative(0.294f, -0.579f, 0.867f, -0.983f, 1.498f, -1.051f)
                lineToRelative(4.047f, -0.517f)
                lineToRelative(-2.87f, -2.927f)
                curveToRelative(-0.459f, -0.461f, -0.656f, -1.12f, -0.531f, -1.765f)
                lineToRelative(0.769f, -4.036f)
                lineToRelative(-3.639f, 1.846f)
                curveToRelative(-0.578f, 0.292f, -1.259f, 0.281f, -1.829f, -0.029f)
                lineToRelative(-3.574f, -1.982f)
                close()
            }
        }
        .build()
        return _vegetableproducts!!
    }

private var _vegetableproducts: ImageVector? = null
