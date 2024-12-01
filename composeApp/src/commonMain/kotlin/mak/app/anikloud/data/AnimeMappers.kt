package mak.app.anikloud.data

import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.domain.model.Anime

internal fun DataDTO.toAnime(): Anime {
    return Anime(
        id = id?.toInt() ?: 0,
        title = attributes?.titles?.en ?: "",
        poster = attributes?.posterImage?.tiny,
        image = attributes?.coverImage?.medium
    )
}

internal fun List<DataDTO>.toModels(): List<Anime> {
    return this.map { it.toAnime() }
}