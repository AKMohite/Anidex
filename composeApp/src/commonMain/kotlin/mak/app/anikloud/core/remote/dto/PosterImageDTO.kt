package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PosterImageDTO(
    @SerialName("tiny")
    var tiny: String? = null,
    @SerialName("large")
    var large: String? = null,
    @SerialName("small")
    var small: String? = null,
    @SerialName("medium")
    var medium: String? = null,
    @SerialName("original")
    var original: String? = null,
//    @SerialName("meta"     ) var meta     : MetaDTO?   = null
)
