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

public val ProductIcons.Instruments: ImageVector
    get() {
        if (_instruments != null) {
            return _instruments!!
        }
        _instruments = Builder(name = "Instruments", defaultWidth = 100.0.dp, defaultHeight =
                100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFECEFF1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(51.367f, 43.588f)
                lineToRelative(0.261f, 0.711f)
                curveToRelative(0.208f, 0.568f, 0.439f, 1.208f, 0.688f, 1.904f)
                curveToRelative(0.534f, 1.492f, 1.068f, 3.015f, 1.582f, 4.521f)
                lineToRelative(0.507f, 1.499f)
                lineToRelative(0.012f, 0.036f)
                curveToRelative(0.906f, 2.706f, 1.59f, 4.873f, 2.11f, 6.732f)
                curveToRelative(0.737f, 2.639f, 1.009f, 3.934f, 1.009f, 5.169f)
                curveToRelative(0.0f, 4.411f, -3.613f, 8.024f, -8.024f, 8.024f)
                reflectiveCurveToRelative(-8.024f, -3.613f, -8.026f, -8.024f)
                curveToRelative(0.0f, -1.235f, 0.273f, -2.53f, 1.009f, -5.169f)
                curveToRelative(0.485f, -1.735f, 1.113f, -3.739f, 1.931f, -6.198f)
                lineToRelative(0.178f, -0.534f)
                lineToRelative(0.012f, -0.036f)
                curveToRelative(0.665f, -1.987f, 1.377f, -4.032f, 2.089f, -6.021f)
                curveToRelative(0.249f, -0.696f, 0.481f, -1.335f, 0.688f, -1.904f)
                lineToRelative(0.263f, -0.714f)
                curveToRelative(0.277f, -0.715f, 0.937f, -1.207f, 1.699f, -1.265f)
                curveToRelative(0.876f, -0.073f, 1.696f, 0.444f, 2.01f, 1.268f)
                close()
                moveTo(49.512f, 50.246f)
                lineToRelative(-0.028f, 0.077f)
                lineToRelative(-0.028f, 0.078f)
                lineToRelative(-0.24f, 0.66f)
                lineToRelative(-0.017f, 0.048f)
                curveToRelative(-0.26f, 0.72f, -0.475f, 1.34f, -0.825f, 2.389f)
                curveToRelative(-0.819f, 2.45f, -1.533f, 4.732f, -2.05f, 6.574f)
                curveToRelative(-0.49f, 1.76f, -0.881f, 3.645f, -0.881f, 4.089f)
                curveToRelative(0.0f, 2.268f, 1.802f, 4.07f, 4.07f, 4.07f)
                curveToRelative(2.266f, 0.0f, 4.068f, -1.802f, 4.068f, -4.07f)
                curveToRelative(0.0f, -0.444f, -0.391f, -2.329f, -0.881f, -4.089f)
                curveToRelative(-0.51f, -1.826f, -1.219f, -4.086f, -2.05f, -6.574f)
                curveToRelative(-0.314f, -0.942f, -0.518f, -1.536f, -0.748f, -2.177f)
                lineToRelative(-0.078f, -0.216f)
                lineToRelative(-0.016f, -0.044f)
                lineToRelative(-0.239f, -0.66f)
                lineToRelative(-0.056f, -0.155f)
                close()
                moveTo(50.143f, 25.913f)
                curveToRelative(20.762f, 0.343f, 37.622f, 17.595f, 37.622f, 38.547f)
                curveToRelative(0.0f, 1.136f, -0.918f, 2.272f, -1.976f, 2.272f)
                horizontalLineToRelative(-7.822f)
                curveToRelative(-1.131f, 0.0f, -1.97f, -0.712f, -1.954f, -1.778f)
                curveToRelative(0.015f, -1.056f, 0.861f, -1.72f, 2.002f, -1.72f)
                horizontalLineToRelative(5.621f)
                curveToRelative(-0.436f, -8.366f, -3.379f, -15.457f, -8.594f, -21.336f)
                lineToRelative(-2.54f, 2.051f)
                lineToRelative(-0.049f, 0.049f)
                lineToRelative(-0.016f, 0.015f)
                curveToRelative(-0.792f, 0.753f, -2.053f, 0.721f, -2.803f, -0.071f)
                curveToRelative(-0.755f, -0.792f, -0.733f, -2.044f, 0.051f, -2.79f)
                lineToRelative(2.54f, -2.568f)
                curveToRelative(-5.944f, -5.351f, -12.607f, -8.228f, -20.849f, -8.654f)
                verticalLineToRelative(5.778f)
                curveToRelative(0.0f, 1.129f, -0.713f, 1.967f, -1.778f, 1.954f)
                curveToRelative(-1.057f, -0.015f, -1.722f, -0.86f, -1.722f, -2.002f)
                verticalLineToRelative(-5.73f)
                curveToRelative(-8.264f, 0.427f, -15.04f, 3.338f, -20.968f, 8.675f)
                lineToRelative(2.414f, 2.59f)
                lineToRelative(0.058f, 0.093f)
                lineToRelative(0.093f, 0.106f)
                curveToRelative(0.608f, 0.75f, 0.593f, 1.879f, -0.036f, 2.61f)
                lineToRelative(-0.117f, 0.125f)
                curveToRelative(-0.803f, 0.782f, -2.05f, 0.857f, -2.784f, 0.102f)
                lineToRelative(-2.567f, -2.323f)
                curveToRelative(-5.207f, 5.878f, -8.146f, 12.965f, -8.582f, 21.326f)
                horizontalLineToRelative(5.669f)
                curveToRelative(1.132f, 0.0f, 1.97f, 0.71f, 1.954f, 1.776f)
                curveToRelative(-0.015f, 1.058f, -0.859f, 1.722f, -2.002f, 1.722f)
                horizontalLineToRelative(-7.774f)
                curveToRelative(-1.058f, 0.0f, -1.976f, -1.136f, -1.976f, -2.272f)
                curveToRelative(0.0f, -21.164f, 17.203f, -38.552f, 38.254f, -38.552f)
                lineToRelative(0.63f, 0.005f)
                close()
            }
        }
        .build()
        return _instruments!!
    }

private var _instruments: ImageVector? = null
