package mak.app.anikloud.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val image: String? = null,
    val poster: String? = null
)
