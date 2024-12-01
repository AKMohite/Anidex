package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DataDTO(
    @SerialName("id") val id: String? = null,
    @SerialName("type") val type: String? = null,
//    @SerialName("links") val links: Links? = Links(),
    @SerialName("attributes") val attributes: AnimeDTO? = null,
//    @SerialName("relationships") val relationships: Relationships? = Relationships()

)