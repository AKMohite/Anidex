package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkDTO(
    @SerialName("first") var first: String? = null,
    @SerialName("next") var next: String? = null,
    @SerialName("last") var last: String? = null
)
