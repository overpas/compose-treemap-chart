package by.overpass.treemapchart.sample.shared.complex

private external object Intl {
    class NumberFormat(locales: String, options: JsAny) {
        fun format(@Suppress("UNUSED_PARAMETER") l: Double): String
    }
}

private fun formatPercentageOptions(): JsAny = js("({ style: 'percent', maximumFractionDigits: 2 })")
private fun formatAsUSDOptions(): JsAny = js("({ style: 'currency', currency: 'USD',})")

internal actual fun Double.formatPercentage(): String {
    val format = Intl.NumberFormat(
        locales = "en-US",
        options = formatPercentageOptions(),
    )
    return format.format(this)
}

internal actual fun Double.formatDollarAmount(): String {
    val format = Intl.NumberFormat(
        locales = "en-US",
        options = formatAsUSDOptions(),
    )
    return format.format(this)
}
