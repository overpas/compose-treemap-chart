package by.overpass.treemapchart.sample.shared.complex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Annotations(
    @SerialName("source_name")
    val sourceName: String,
    @SerialName("dataset_link")
    val datasetLink: String,
    @SerialName("topic")
    val topic: String,
    @SerialName("dataset_name")
    val datasetName: String,
    @SerialName("subtopic")
    val subtopic: String,
    @SerialName("source_description")
    val sourceDescription: String,
    @SerialName("table")
    val table: String,
)
