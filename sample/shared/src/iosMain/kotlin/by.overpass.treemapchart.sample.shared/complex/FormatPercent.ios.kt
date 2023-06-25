package by.overpass.treemapchart.sample.shared.complex

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

internal actual fun Double.formatPercent(): String = with(NSNumberFormatter()) {
    maximumFractionDigits = 2L.toULong()
    numberStyle = NSNumberFormatterDecimalStyle
    stringFromNumber(NSNumber(this@formatPercent)) ?: this@formatPercent.toString()
}