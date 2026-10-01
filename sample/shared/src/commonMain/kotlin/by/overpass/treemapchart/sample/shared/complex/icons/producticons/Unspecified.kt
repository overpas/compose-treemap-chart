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

private val unspecified: ImageVector by lazy {
    Builder(
        name = "Unspecified",
        defaultWidth = 25.0.dp,
        defaultHeight =
        25.0.dp,
        viewportWidth = 25.0f,
        viewportHeight = 25.0f,
    ).apply {
        path(
            fill = SolidColor(Color(0xFFCFDAE2)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero,
        ) {
            moveTo(2.82f, 23.536f)
            curveToRelative(dx1 = -0.15f, dy1 = 0.0f, dx2 = -0.271f, dy2 = -0.116f, dx3 = -0.271f, dy3 = -0.258f)
            verticalLineToRelative(-15.612f)
            horizontalLineToRelative(19.903f)
            verticalLineToRelative(15.612f)
            curveToRelative(dx1 = 0.0f, dy1 = 0.142f, dx2 = -0.121f, dy2 = 0.258f, dx3 = -0.271f, dy3 = 0.258f)
            horizontalLineToRelative(-19.361f)
            close()
            moveTo(6.234f, 1.465f)
            horizontalLineToRelative(5.544f)
            verticalLineToRelative(4.737f)
            horizontalLineToRelative(-8.427f)
            lineToRelative(2.884f, -4.737f)
            close()
            moveTo(13.325f, 1.465f)
            horizontalLineToRelative(5.492f)
            lineToRelative(2.893f, 4.737f)
            horizontalLineToRelative(-8.385f)
            verticalLineToRelative(-4.737f)
            close()
            moveTo(23.876f, 6.868f)
            lineToRelative(-0.093f, -0.152f)
            lineToRelative(-3.675f, -6.012f)
            curveToRelative(dx1 = -0.264f, dy1 = -0.433f, dx2 = -0.757f, dy2 = -0.702f, dx3 = -1.285f, dy3 = -0.702f)
            horizontalLineToRelative(-12.646f)
            curveToRelative(dx1 = -0.53f, dy1 = 0.0f, dx2 = -1.023f, dy2 = 0.269f, dx3 = -1.286f, dy3 = 0.704f)
            lineToRelative(-3.734f, 6.132f)
            lineToRelative(-0.076f, 0.172f)
            curveToRelative(dx1 = -0.052f, dy1 = 0.134f, dx2 = -0.082f, dy2 = 0.28f, dx3 = -0.082f, dy3 = 0.431f)
            verticalLineToRelative(15.839f)
            curveToRelative(dx1 = 0.0f, dy1 = 0.951f, dx2 = 0.816f, dy2 = 1.722f, dx3 = 1.821f, dy3 = 1.722f)
            horizontalLineToRelative(19.359f)
            curveToRelative(dx1 = 1.006f, dy1 = 0.0f, dx2 = 1.821f, dy2 = -0.771f, dx3 = 1.821f, dy3 = -1.722f)
            verticalLineToRelative(-15.897f)
            lineToRelative(-0.002f, -0.063f)
            curveToRelative(dx1 = 0.011f, dy1 = -0.142f, dx2 = -0.023f, dy2 = -0.287f, dx3 = -0.103f, dy3 = -0.418f)
            lineToRelative(-0.02f, -0.033f)
            close()
        }
    }
        .build()
}

public val ProductIcons.Unspecified: ImageVector
    get() = unspecified
