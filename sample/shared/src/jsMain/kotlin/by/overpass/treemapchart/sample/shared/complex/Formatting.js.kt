package by.overpass.treemapchart.sample.shared.complex

private external object Intl {
    class NumberFormat(locales: String, options: dynamic) {
        fun format(value: Double): String
    }
}

internal actual fun Double.formatPercentage(): String {
    val format = Intl.NumberFormat(
        locales = "en-US",
        options = js("{ style: 'percent', maximumFractionDigits: 2 }"),
    )
    return format.format(this)
}

internal actual fun Double.formatDollarAmount(): String {
    val format = Intl.NumberFormat(
        locales = "en-US",
        options = js("{ style: 'currency', currency: 'USD', maximumFractionDigits: 1 }"),
    )
    return format.format(this)
}
