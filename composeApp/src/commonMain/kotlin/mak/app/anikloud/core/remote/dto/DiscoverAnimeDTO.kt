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
    val links: LinkDTO? = null
)

