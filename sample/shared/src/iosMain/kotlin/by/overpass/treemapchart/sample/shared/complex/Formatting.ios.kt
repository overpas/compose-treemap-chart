package by.overpass.treemapchart.sample.shared.complex

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle
import platform.Foundation.NSNumberFormatterPercentStyle

internal actual fun Double.formatPercentage(): String {
    val formatter = NSNumberFormatter().apply {
        maximumFractionDigits = 2.toULong()
        numberStyle = NSNumberFormatterPercentStyle
    }
    return formatter.stringFromNumber(NSNumber(this)) ?: toString()
}

internal actual fun Double.formatDollarAmount(): String {
    val formatter = NSNumberFormatter().apply {
        maximumFractionDigits = 1.toULong()
        numberStyle = NSNumberFormatterCurrencyStyle
        currencyCode = "USD"
        locale = NSLocale("en_US")
    }
    return formatter.stringFromNumber(NSNumber(this)) ?: toString()
}
