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

public val ProductIcons.PreciousMetals: ImageVector
    get() {
        if (_preciousmetals != null) {
            return _preciousmetals!!
        }
        _preciousmetals = Builder(name = "Preciousmetals", defaultWidth = 100.0.dp, defaultHeight =
                100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(77.314f, 21.0f)
                lineToRelative(11.822f, 18.545f)
                lineToRelative(-39.343f, 42.455f)
                lineToRelative(-39.341f, -42.451f)
                lineToRelative(12.043f, -18.549f)
                horizontalLineToRelative(54.819f)
                close()
                moveTo(61.428f, 42.987f)
                horizontalLineToRelative(-23.265f)
                lineToRelative(11.631f, 33.149f)
                lineToRelative(11.634f, -33.149f)
                close()
                moveTo(33.969f, 42.987f)
                horizontalLineToRelative(-14.909f)
                lineToRelative(23.255f, 25.072f)
                lineToRelative(-8.346f, -25.072f)
                close()
                moveTo(80.527f, 42.983f)
                horizontalLineToRelative(-14.907f)
                lineToRelative(-8.348f, 25.072f)
                lineToRelative(23.255f, -25.072f)
                close()
                moveTo(77.314f, 28.319f)
                lineToRelative(-9.325f, 10.677f)
                horizontalLineToRelative(16.0f)
                lineToRelative(-6.674f, -10.677f)
                close()
                moveTo(22.495f, 28.319f)
                lineToRelative(-6.897f, 10.677f)
                horizontalLineToRelative(15.994f)
                lineToRelative(-9.097f, -10.677f)
                close()
                moveTo(49.748f, 24.994f)
                lineToRelative(-12.044f, 14.002f)
                horizontalLineToRelative(24.175f)
                lineToRelative(-12.131f, -14.002f)
                close()
                moveTo(44.817f, 24.994f)
                horizontalLineToRelative(-19.959f)
                lineToRelative(9.791f, 11.362f)
                lineToRelative(10.168f, -11.362f)
                close()
                moveTo(74.729f, 24.994f)
                horizontalLineToRelative(-19.988f)
                lineToRelative(10.19f, 11.362f)
                lineToRelative(9.799f, -11.362f)
                close()
            }
        }
        .build()
        return _preciousmetals!!
    }

private var _preciousmetals: ImageVector? = null
