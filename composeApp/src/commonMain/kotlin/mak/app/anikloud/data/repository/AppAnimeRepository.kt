package mak.app.anikloud.data.repository

import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.common.model.map
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.core.remote.dto.IncludeDTO
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_INCLUDE_GENRE
import mak.app.anikloud.data.toEntities
import mak.app.anikloud.data.toModels
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.repository.AnimeRepository

internal class AppAnimeRepository(
    private val api: AnimeAPI,
    private val dao: AnimeDAO,
    private val transactionRunner: DatabaseTransactionRunner
) : AnimeRepository {
    override suspend fun getAiringAnime(): AppResult<List<Anime>, DataError> {
        val apiResult = api.getAiringAnimeResult()
        return apiResult
            .map { dto ->
                val genresMap = getAnimeGenres(dto)
                val allGenres = genresMap.flatMap {
                    it.value
                }.distinctBy { it.id }
                val entities = dto.data.toEntities()
                transactionRunner {
                    dao.insert(entities)
                }
                println(allGenres)
                entities.toModels()
//                dto.data.toModels()
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
                    it.id == genre.id && it.type == QUERY_INCLUDE_GENRE
                }
            } ?: emptyList()
            id to genres
        }
        return a.toMap()
    }
}