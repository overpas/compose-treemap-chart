package by.overpass.treemapchart.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView

/**
 * Basic treemap item view: the text in the center, with a 1dp border in the text color.
 */
class SimpleTreemapItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle,
) : TextView(context, attrs, defStyleAttr) {

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
    }

    init {
        gravity = Gravity.CENTER
        textAlignment = TEXT_ALIGNMENT_CENTER
        val typedValue = TypedValue()
        val hasThemeColor = context.theme.resolveAttribute(android.R.attr.textColorPrimary, typedValue, true)
        if (attrs == null && hasThemeColor) {
            setThemeTextColor(typedValue)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        borderPaint.color = currentTextColor
        val inset = borderPaint.strokeWidth / 2
        canvas.drawRect(
            scrollX + inset,
            scrollY + inset,
            scrollX + width - inset,
            scrollY + height - inset,
            borderPaint,
        )
    }

    private fun setThemeTextColor(typedValue: TypedValue) {
        if (typedValue.resourceId == 0) {
            setTextColor(typedValue.data)
        } else {
            setTextColor(context.getColorStateList(typedValue.resourceId))
        }
    }
}
