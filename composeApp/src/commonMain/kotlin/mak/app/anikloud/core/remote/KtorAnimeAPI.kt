package mak.app.anikloud.core.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.core.remote.ext.safeCall
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_CURRENT
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_DATA_LIMIT
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_INCLUDE
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_INCLUDE_GENRE
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_LIMIT
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_OFFSET
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_SORT
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_SORT_USER_COUNT
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_STATUS

internal class KtorAnimeAPI(
    private val client: HttpClient
) : AnimeAPI {

    override suspend fun getAiringAnime(
        page: Int
    ): AppResult<DiscoverAnimeDTO, DataError.Remote> {
        return safeCall {
            client
            client.get {
                url("anime")
                parameter(QUERY_STATUS, QUERY_CURRENT)
                parameter(QUERY_LIMIT, QUERY_DATA_LIMIT)
                parameter(QUERY_OFFSET, getPageOffset(page))
                parameter(QUERY_SORT, QUERY_SORT_USER_COUNT)
                parameter(QUERY_INCLUDE, QUERY_INCLUDE_GENRE)
            }
        }
    }

    private fun getPageOffset(page: Int): Int {
        if (page < 1) throw IllegalArgumentException("Invalid page found: $page")
        return (page - 1) * QUERY_DATA_LIMIT
    }
}