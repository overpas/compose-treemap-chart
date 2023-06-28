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

public val ProductIcons.AnimalAndVegetableBiProducts: ImageVector
    get() {
        if (_animalandvegetablebiproducts != null) {
            return _animalandvegetablebiproducts!!
        }
        _animalandvegetablebiproducts = Builder(name = "Animalandvegetablebiproducts", defaultWidth
                = 100.0.dp, defaultHeight = 100.0.dp, viewportWidth = 100.0f, viewportHeight =
                100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(66.633f, 9.0f)
                curveToRelative(1.128f, 0.0f, 2.04f, 0.903f, 2.04f, 2.018f)
                lineToRelative(-0.007f, 12.925f)
                lineToRelative(3.293f, 16.289f)
                lineToRelative(0.023f, 0.13f)
                lineToRelative(0.013f, 0.133f)
                lineToRelative(0.004f, 0.135f)
                verticalLineToRelative(48.353f)
                curveToRelative(0.0f, 1.116f, -0.913f, 2.018f, -2.042f, 2.018f)
                horizontalLineToRelative(-39.917f)
                curveToRelative(-1.127f, 0.0f, -2.04f, -0.902f, -2.04f, -2.018f)
                verticalLineToRelative(-48.353f)
                curveToRelative(0.0f, -0.139f, 0.014f, -0.273f, 0.042f, -0.397f)
                lineToRelative(3.285f, -16.217f)
                verticalLineToRelative(-12.998f)
                curveToRelative(0.0f, -1.115f, 0.914f, -2.018f, 2.042f, -2.018f)
                horizontalLineToRelative(33.265f)
                close()
                moveTo(64.959f, 26.198f)
                horizontalLineToRelative(-29.92f)
                lineToRelative(-2.957f, 14.595f)
                verticalLineToRelative(46.169f)
                horizontalLineToRelative(35.835f)
                lineToRelative(0.007f, -46.096f)
                lineToRelative(-2.965f, -14.668f)
                close()
                moveTo(49.109f, 58.452f)
                curveToRelative(0.636f, -0.602f, 1.632f, -0.602f, 2.268f, 0.0f)
                curveToRelative(1.188f, 1.126f, 7.112f, 6.95f, 7.112f, 11.092f)
                curveToRelative(0.0f, 4.546f, -3.7f, 8.246f, -8.246f, 8.246f)
                reflectiveCurveToRelative(-8.244f, -3.7f, -8.244f, -8.246f)
                curveToRelative(0.0f, -4.142f, 5.922f, -9.966f, 7.11f, -11.092f)
                close()
                moveTo(50.243f, 61.978f)
                curveToRelative(-2.336f, 2.446f, -4.948f, 5.836f, -4.948f, 7.566f)
                curveToRelative(0.0f, 2.728f, 2.222f, 4.948f, 4.948f, 4.948f)
                curveToRelative(2.728f, 0.0f, 4.948f, -2.22f, 4.948f, -4.948f)
                curveToRelative(0.0f, -1.732f, -2.61f, -5.126f, -4.948f, -7.566f)
                close()
                moveTo(58.999f, 39.001f)
                curveToRelative(1.105f, 0.0f, 2.0f, 0.895f, 2.0f, 2.0f)
                reflectiveCurveToRelative(-0.895f, 2.0f, -2.0f, 2.0f)
                horizontalLineToRelative(-18.0f)
                curveToRelative(-1.105f, 0.0f, -2.0f, -0.895f, -2.0f, -2.0f)
                reflectiveCurveToRelative(0.895f, -2.0f, 2.0f, -2.0f)
                horizontalLineToRelative(18.0f)
                close()
                moveTo(64.59f, 13.039f)
                horizontalLineToRelative(-29.182f)
                verticalLineToRelative(9.122f)
                horizontalLineToRelative(29.182f)
                verticalLineToRelative(-9.122f)
                close()
            }
        }
        .build()
        return _animalandvegetablebiproducts!!
    }

private var _animalandvegetablebiproducts: ImageVector? = null
