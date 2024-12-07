package mak.app.anikloud.core.remote

import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO

internal interface AnimeAPI {
    suspend fun getAiringAnimeResult(
        page: Int = 1
    ): AppResult<DiscoverAnimeDTO, DataError.Remote>

    suspend fun getAiringAnime(
        page: Int = 1
    ): DiscoverAnimeDTO

    suspend fun getTrendingAnime(
        page: Int = 1
    ): DiscoverAnimeDTO

    suspend fun highestRatedAnime(
        page: Int = 1
    ): DiscoverAnimeDTO

    suspend fun popularAnime(
        page: Int
    ): DiscoverAnimeDTO

    suspend fun anticipatedAnime(
        page: Int
    ): DiscoverAnimeDTO
}
