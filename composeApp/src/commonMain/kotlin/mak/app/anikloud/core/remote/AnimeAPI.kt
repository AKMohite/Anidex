package mak.app.anikloud.core.remote

import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO

internal interface AnimeAPI {
    suspend fun getAiringAnime(
        page: Int = 1
    ): AppResult<DiscoverAnimeDTO, DataError.Remote>
}
