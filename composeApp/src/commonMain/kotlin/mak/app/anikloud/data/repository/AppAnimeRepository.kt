package mak.app.anikloud.data.repository

import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.common.model.map
import mak.app.anikloud.core.remote.AnimeAPI
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
                dto.data.toModels()
            }
    }
}