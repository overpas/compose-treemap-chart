package by.overpass.treemapchart.sample.shared.complex.icons.producticons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.sample.shared.complex.icons.ProductIcons

private val miscellaneous: ImageVector by lazy {
    Builder(
        name = "Miscellaneous",
        defaultWidth = 100.0.dp,
        defaultHeight =
        100.0.dp,
        viewportWidth = 100.0f,
        viewportHeight = 100.0f,
    ).apply {
        path(
            fill = SolidColor(Color(0xFFECEFF1)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero,
        ) {
            moveTo(50.274f, 14.254f)
            lineToRelative(31.431f, 12.545f)
            curveToRelative(dx1 = 0.771f, dy1 = 0.313f, dx2 = 1.302f, dy2 = 1.109f, dx3 = 1.295f, dy3 = 1.935f)
            verticalLineToRelative(42.345f)
            curveToRelative(dx1 = -0.006f, dy1 = 0.819f, dx2 = -0.535f, dy2 = 1.596f, dx3 = -1.298f, dy3 = 1.905f)
            lineToRelative(-31.427f, 12.545f)
            curveToRelative(dx1 = -0.493f, dy1 = 0.198f, dx2 = -1.058f, dy2 = 0.198f, dx3 = -1.55f, dy3 = 0.001f)
            lineToRelative(-31.43f, -12.547f)
            curveToRelative(dx1 = -0.761f, dy1 = -0.308f, dx2 = -1.289f, dy2 = -1.085f, dx3 = -1.295f, dy3 = -1.907f)
            verticalLineToRelative(-42.337f)
            curveToRelative(dx1 = -0.007f, dy1 = -0.831f, dx2 = 0.523f, dy2 = -1.627f, dx3 = 1.298f, dy3 = -1.941f)
            lineToRelative(31.465f, -12.557f)
            curveToRelative(dx1 = 0.213f, dy1 = -0.066f, dx2 = 0.435f, dy2 = -0.109f, dx3 = 0.663f, dy3 = -0.12f)
            curveToRelative(dx1 = 0.293f, dy1 = -0.014f, dx2 = 0.574f, dy2 = 0.025f, dx3 = 0.848f, dy3 = 0.133f)
            close()
            moveTo(78.855f, 31.775f)
            lineToRelative(-11.568f, 4.623f)
            verticalLineToRelative(11.205f)
            curveToRelative(dx1 = -0.016f, dy1 = 1.142f, dx2 = -0.959f, dy2 = 2.057f, dx3 = -2.101f, dy3 = 2.039f)
            curveToRelative(dx1 = -1.145f, dy1 = -0.016f, dx2 = -2.061f, dy2 = -0.956f, dx3 = -2.045f, dy3 = -2.09f)
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
}

public val ProductIcons.Miscellaneous: ImageVector
    get() = miscellaneous
