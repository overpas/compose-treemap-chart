package by.overpass.treemapchart.sample.shared.complex

import java.util.Locale

internal actual fun Double.formatPercentage(): String =
    String.format(Locale.getDefault(), "%.2f", this) + "%"

internal actual fun Double.formatDollarAmount(): String =
    "$" + String.format(Locale.getDefault(), "%.1f", this)
