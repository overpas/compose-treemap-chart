package by.overpass.treemapchart.sample.shared.complex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ExportsResponse(
    @SerialName("data")
    val data: List<ProductTrade>,
    @SerialName("source")
    val source: List<Source>,
)
