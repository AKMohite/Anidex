package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MetaInfoDTO(
    @SerialName("count")
    val count: Int? = null
)
