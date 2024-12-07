package mak.app.anikloud.data

import mak.app.anikloud.core.database.dao.AiringAnimeEntity
import mak.app.anikloud.core.database.dao.AnimeEntity
import mak.app.anikloud.core.database.dao.PopularAnimeEntity
import mak.app.anikloud.core.database.dao.TrendingAnimeEntity
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

internal fun List<DataDTO>.toDomainModels(): List<Anime> {
    return this.map { it.toAnime() }
}

internal fun DataDTO.toEntity(): AnimeEntity = AnimeEntity(
    id = id?.toLong() ?: 0L,
    title = attributes?.titles?.en ?: "",
    description = attributes?.description ?: "",
    image = attributes?.posterImage?.tiny,
    cover_image = attributes?.coverImage?.medium,
    genres = "" // TODO map genres from repository
)

internal fun List<DataDTO>.toEntities() = this.map { it.toEntity() }


internal fun List<DataDTO>.toPopularEntities(page: Int) = this.map { it.toPopularEntity(page) }

private fun DataDTO.toPopularEntity(page: Int) = PopularAnimeEntity(
    id = 0L,
    anime_id = id!!,
    page = page
)

internal fun List<DataDTO>.toAiringEntities(page: Int) = this.map { it.toAiringEntity(page) }

private fun DataDTO.toAiringEntity(page: Int) = AiringAnimeEntity(
    id = 0L,
    anime_id = id!!,
    page = page
)

internal fun List<DataDTO>.toTrendingEntities(page: Int) = this.map { it.toTrendingEntity(page) }

private fun DataDTO.toTrendingEntity(page: Int) = TrendingAnimeEntity(
    id = 0L,
    anime_id = id!!,
    page = page
)

internal fun List<AnimeEntity>.toModels() = this.map { it.toModel() }

internal fun AnimeEntity.toModel(): Anime {
    return Anime(
        id = id,
        title = title,
        poster = image,
        coverImage = cover_image
    )
}
