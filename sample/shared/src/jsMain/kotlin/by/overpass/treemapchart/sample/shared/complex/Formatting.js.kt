package by.overpass.treemapchart.sample.shared.complex

import js.intl.NumberFormat

internal actual fun Double.formatPercentage(): String {
    val format = NumberFormat(
        locales = emptyArray(),
        options = js("{ style: 'percent', maximumFractionDigits: 2 }"),
    )
    return format.format(this)
}

internal actual fun Double.formatDollarAmount(): String {
    val format = NumberFormat(
        locales = "en-US",
        options = js("{ style: 'currency', currency: 'USD', maximumFractionDigits: 1 }"),
    )
    return format.format(this)
}
