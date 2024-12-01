package mak.app.anikloud.domain.repository

import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.domain.model.Anime

internal interface AnimeRepository {
    suspend fun getAiringAnime(): AppResult<List<Anime>, DataError>
}
