package by.overpass.treemapchart.sample.shared.complex

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

internal actual fun Double.formatPercentage(): String {
    val formatter = decimalFormatter(2)
    val stringFromNumber = formatter.stringFromNumber(NSNumber(this)) ?: toString()
    return "$stringFromNumber%"
}

internal actual fun Double.formatDollarAmount(): String {
    val formatter = decimalFormatter(1)
    val stringFromNumber = formatter.stringFromNumber(NSNumber(this)) ?: toString()
    return "$$stringFromNumber"
}

private fun decimalFormatter(fractionDigits: Int): NSNumberFormatter = NSNumberFormatter().apply {
    maximumFractionDigits = fractionDigits.toULong()
    numberStyle = NSNumberFormatterDecimalStyle
}
