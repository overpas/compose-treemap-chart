package by.overpass.treemapchart.sample.shared.complex

import java.text.NumberFormat
import java.util.Locale

internal actual fun Double.formatPercentage(): String =
    NumberFormat.getPercentInstance()
        .apply {
            maximumFractionDigits = 2
        }
        .format(this)

internal actual fun Double.formatDollarAmount(): String =
    NumberFormat.getCurrencyInstance(Locale.US)
        .apply {
            maximumFractionDigits = 1
        }
        .format(this)
