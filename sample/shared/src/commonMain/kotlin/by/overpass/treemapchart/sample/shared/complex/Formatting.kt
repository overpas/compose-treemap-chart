package by.overpass.treemapchart.sample.shared.complex

internal expect fun Double.formatPercentage(): String

internal fun Double.formatTradeValue(): String {
    val billions = this / 1000000000
    val millions = this / 1000000
    return if (billions >= 1) {
        "${billions.formatDollarAmount()}B"
    } else if (millions >= 1) {
        "${millions.formatDollarAmount()}M"
    } else {
        formatDollarAmount()
    }
}

internal expect fun Double.formatDollarAmount(): String

