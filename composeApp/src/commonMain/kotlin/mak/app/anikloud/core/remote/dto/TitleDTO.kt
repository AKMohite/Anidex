package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TitleDTO(
    @SerialName("en")
    val en: String? = null,
    @SerialName("en_jp")
    val enJp: String? = null,
    @SerialName("ja_jp")
    val jaJp: String? = null

)
