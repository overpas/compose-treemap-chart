package by.overpass.treemapchart.sample.shared.complex

import java.util.Locale

internal actual fun Double.formatPercent(): String =
    String.format(Locale.getDefault(), "%.2f", this) + "%"