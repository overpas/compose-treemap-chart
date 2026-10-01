package by.overpass.treemapchart.sample.shared.complex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Source(
    @SerialName("name")
    val name: String,
    @SerialName("measures")
    val measures: List<String>,
    @SerialName("annotations")
    val annotations: Annotations,
)
