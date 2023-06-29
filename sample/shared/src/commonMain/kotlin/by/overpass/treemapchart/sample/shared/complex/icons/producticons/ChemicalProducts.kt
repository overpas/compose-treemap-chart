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

public val ProductIcons.ChemicalProducts: ImageVector
    get() {
        if (_chemicalproducts != null) {
            return _chemicalproducts!!
        }
        _chemicalproducts = Builder(name = "Chemicalproducts", defaultWidth = 100.0.dp,
                defaultHeight = 100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(64.999f, 18.0f)
                lineToRelative(0.024f, 3.984f)
                lineToRelative(-5.027f, -0.001f)
                verticalLineToRelative(21.068f)
                lineToRelative(26.46f, 35.949f)
                horizontalLineToRelative(-71.684f)
                lineToRelative(26.228f, -35.945f)
                lineToRelative(-0.001f, -21.072f)
                lineToRelative(-4.999f, 0.001f)
                verticalLineToRelative(-3.984f)
                horizontalLineToRelative(28.998f)
                close()
                moveTo(67.511f, 59.999f)
                horizontalLineToRelative(-33.893f)
                lineToRelative(-10.982f, 15.017f)
                horizontalLineToRelative(55.928f)
                lineToRelative(-11.053f, -15.017f)
                close()
                moveTo(55.996f, 22.0f)
                horizontalLineToRelative(-10.932f)
                verticalLineToRelative(22.352f)
                lineToRelative(-8.532f, 11.663f)
                horizontalLineToRelative(28.047f)
                lineToRelative(-8.583f, -11.66f)
                verticalLineToRelative(-22.355f)
                close()
            }
        }
        .build()
        return _chemicalproducts!!
    }

private var _chemicalproducts: ImageVector? = null
