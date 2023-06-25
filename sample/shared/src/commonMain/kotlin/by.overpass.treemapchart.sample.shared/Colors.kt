package by.overpass.treemapchart.sample.shared

import androidx.compose.ui.graphics.Color

internal fun String?.asColor(): Color? =
    this?.asColorInt()?.let(::Color)

internal fun String.asColorInt(): Int {
    val colorHex = removePrefix("#")
    fun hexToDecimal(hex: String): Int {
        var decimal = 0
        for (i in hex.indices) {
            val digit = hex[i].code
            decimal = (decimal shl 4) or (digit - if (digit >= '0'.code && digit <= '9'.code) {
                '0'.code
            } else {
                'A'.code - 10
            })
        }
        return decimal
    }

    val alpha = hexToDecimal(colorHex.substring(0, 2))
    val red = hexToDecimal(colorHex.substring(2, 4))
    val green = hexToDecimal(colorHex.substring(4, 6))
    val blue = hexToDecimal(colorHex.substring(6, 8))
    return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
}