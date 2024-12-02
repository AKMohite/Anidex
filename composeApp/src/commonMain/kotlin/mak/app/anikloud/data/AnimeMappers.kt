package mak.app.anikloud.data

import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.domain.model.Anime

internal fun DataDTO.toAnime(): Anime {
    return Anime(
        id = id?.toLong() ?: 0,
        title = attributes?.titles?.en ?: "",
        poster = attributes?.posterImage?.tiny,
        coverImage = attributes?.coverImage?.medium
    )
}

internal fun List<DataDTO>.toModels(): List<Anime> {
    return this.map { it.toAnime() }
}

//internal fun DataDTO.toEntity(): AnimeEntity = AnimeEntity(
//    id = id?.toLong() ?: 0L,
//    title = attributes?.titles?.en ?: "",
//    description = attributes?.description ?: "",
//    image = attributes?.posterImage?.tiny,
//    coverImage = attributes?.coverImage?.medium,
//    genres = emptyList() // TODO map genres from repository
//)
//
//internal fun List<DataDTO>.toEntities() = this.map { it.toEntity() }
//
//internal fun List<AnimeEntity>.toModels() = this.map { it.toModel() }
//
//internal fun AnimeEntity.toModel(): Anime {
//    return Anime(
//        id = id,
//        title = title,
//        poster = image,
//        coverImage = coverImage
//    )
//}
