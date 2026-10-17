package by.overpass.treemapchart.sample.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import kotlin.math.ceil
import kotlin.math.min

internal class BorderDrawable(
    context: Context,
    widthDp: Float,
) : Drawable() {

    private val strokeWidth = ceil(widthDp * context.resources.displayMetrics.density)

    private val paint = Paint().apply {
        color = context.getColor(R.color.on_background)
    }

    override fun draw(canvas: Canvas) {
        val left = bounds.left.toFloat()
        val top = bounds.top.toFloat()
        val right = bounds.right.toFloat()
        val bottom = bounds.bottom.toFloat()
        val width = min(strokeWidth, ceil(min(right - left, bottom - top) / 2))
        canvas.drawRect(left, top, right, top + width, paint)
        canvas.drawRect(left, bottom - width, right, bottom, paint)
        canvas.drawRect(left, top, left + width, bottom, paint)
        canvas.drawRect(right - width, top, right, bottom, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int =
        PixelFormat.TRANSLUCENT
}
