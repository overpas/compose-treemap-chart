package by.overpass.treemapchart.sample.shared.complex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductTrade(
    @SerialName("Section ID")
    val sectionId: Long,
    @SerialName("Section")
    val sectionName: String,
    @SerialName("HS2 ID")
    val hs2Id: Long,
    @SerialName("HS2")
    val hs2Name: String,
    @SerialName("HS4 ID")
    val hs4Id: Long,
    @SerialName("HS4")
    val hs4Name: String,
    @SerialName("Trade Value")
    val tradeValue: Double,
)
