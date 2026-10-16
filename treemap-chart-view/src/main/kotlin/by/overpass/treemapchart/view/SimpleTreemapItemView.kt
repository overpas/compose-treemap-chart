package by.overpass.treemapchart.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView

class SimpleTreemapItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = android.R.style.TextAppearance_Material_Subhead,
) : TextView(context, attrs, defStyleAttr, defStyleRes) {

    private val borderWidth = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        1f,
        resources.displayMetrics,
    )

    private val borderPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = borderWidth
    }

    init {
        gravity = Gravity.CENTER
        textAlignment = TEXT_ALIGNMENT_CENTER
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val inset = borderWidth / 2
        borderPaint.color = currentTextColor
        canvas.drawRect(inset, inset, width - inset, height - inset, borderPaint)
    }
}
