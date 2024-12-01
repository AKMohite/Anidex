package mak.app.anikloud.data.repository

import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.common.model.map
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.core.remote.dto.IncludeDTO
import mak.app.anikloud.data.toModels
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.repository.AnimeRepository

internal class AppAnimeRepository(
    private val api: AnimeAPI
) : AnimeRepository {
    override suspend fun getAiringAnime(): AppResult<List<Anime>, DataError> {
        val apiResult = api.getAiringAnime()
        return apiResult
            .map { dto ->
                val genresMap = getAnimeGenres(dto)
                val allGenres = genresMap.flatMap {
                    it.value
                }.distinctBy { it.id }
                println(allGenres)
                dto.data.toModels()
            }
    }

    private fun getAnimeGenres(dto: DiscoverAnimeDTO): Map<String, List<IncludeDTO>> {
        val data = dto.data.filterNot { it.id.isNullOrBlank() && it.relationships != null }.map {
            Pair(it.id!!, it.relationships)
        }
        val allGenres = dto.included?: return emptyMap()
        val a = data.map { (id, relation) ->
            val genres = relation?.type?.genres?.mapNotNull { genre ->
                allGenres.firstOrNull {
                    it.id == genre.id
                }
            } ?: emptyList()
            id to genres
        }
        return a.toMap()
    }
}