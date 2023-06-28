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

public val ProductIcons.Unspecified: ImageVector
    get() {
        if (_unspecified != null) {
            return _unspecified!!
        }
        _unspecified = Builder(name = "Unspecified", defaultWidth = 25.0.dp, defaultHeight =
                25.0.dp, viewportWidth = 25.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0xFFCFDAE2)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.82f, 23.536f)
                curveToRelative(-0.15f, 0.0f, -0.271f, -0.116f, -0.271f, -0.258f)
                verticalLineToRelative(-15.612f)
                horizontalLineToRelative(19.903f)
                verticalLineToRelative(15.612f)
                curveToRelative(0.0f, 0.142f, -0.121f, 0.258f, -0.271f, 0.258f)
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
                curveToRelative(-0.264f, -0.433f, -0.757f, -0.702f, -1.285f, -0.702f)
                horizontalLineToRelative(-12.646f)
                curveToRelative(-0.53f, 0.0f, -1.023f, 0.269f, -1.286f, 0.704f)
                lineToRelative(-3.734f, 6.132f)
                lineToRelative(-0.076f, 0.172f)
                curveToRelative(-0.052f, 0.134f, -0.082f, 0.28f, -0.082f, 0.431f)
                verticalLineToRelative(15.839f)
                curveToRelative(0.0f, 0.951f, 0.816f, 1.722f, 1.821f, 1.722f)
                horizontalLineToRelative(19.359f)
                curveToRelative(1.006f, 0.0f, 1.821f, -0.771f, 1.821f, -1.722f)
                verticalLineToRelative(-15.897f)
                lineToRelative(-0.002f, -0.063f)
                curveToRelative(0.011f, -0.142f, -0.023f, -0.287f, -0.103f, -0.418f)
                lineToRelative(-0.02f, -0.033f)
                close()
            }
        }
        .build()
        return _unspecified!!
    }

private var _unspecified: ImageVector? = null
