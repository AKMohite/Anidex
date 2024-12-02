package mak.app.anikloud.domain.model

data class Anime(
    val id: Long,
    val title: String,
    val coverImage: String? = null,
    val poster: String? = null
)
