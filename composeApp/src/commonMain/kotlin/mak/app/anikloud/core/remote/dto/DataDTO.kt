package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DataDTO(
    @SerialName("id") val id: String? = null,
    @SerialName("type") val type: String? = null,
//    @SerialName("links") val links: Links? = Links(),
    @SerialName("attributes") val attributes: AnimeDTO? = null,
    @SerialName("relationships") val relationships: RelationshipDTO? = null
)

// Genres
@Serializable
internal data class RelationshipDTO(
    @SerialName("categories") val type: CategoryDTO? = null,
)

@Serializable
internal data class CategoryDTO(
    @SerialName("data") val genres: List<GenreDTO>? = null,
)

@Serializable
data class GenreDTO(
    @SerialName("type") val type: String? = null,
    @SerialName("id") val id: String? = null,
)
