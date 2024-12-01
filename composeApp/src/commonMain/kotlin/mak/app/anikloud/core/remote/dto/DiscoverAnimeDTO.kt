package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DiscoverAnimeDTO(
    @SerialName("data")
    val data: List<DataDTO> = emptyList(),
    @SerialName("meta")
    val meta: MetaInfoDTO? = null,
    @SerialName("links")
    val links: LinkDTO? = null,
    @SerialName("included")
    val included: List<IncludeDTO>? = null
)

@Serializable
internal data class IncludeDTO(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("attributes")
    val attributes: AttributeDTO? = null,
)

@Serializable
internal data class AttributeDTO(
    @SerialName("title")
    val title: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("description")
    val description: String? = null
)

