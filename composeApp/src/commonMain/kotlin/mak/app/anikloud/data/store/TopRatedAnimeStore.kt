package mak.app.anikloud.data.store

import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.TopRatedAnimeDAO
import mak.app.anikloud.core.database.dao.TopRatedAnimeEntity
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.data.toTopRatedEntities
import mak.app.anikloud.domain.model.SyncRequest


internal class TopRatedAnimeStore(
    private val api: AnimeAPI,
    animeDAO: AnimeDAO,
    topRatedAnimeDAO: TopRatedAnimeDAO,
    lastSyncDao: LastSyncDAO,
    transactionRunner: DatabaseTransactionRunner,
    dispatcher: Dispatcher
): OfflineAnimeStore<TopRatedAnimeEntity>(
    animeDAO = animeDAO,
    tableDAO = topRatedAnimeDAO,
    lastSyncDao = lastSyncDao,
    transactionRunner = transactionRunner,
    dispatcher = dispatcher,
    syncRequest = SyncRequest.TOP_RATED_ANIME
) {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<TopRatedAnimeEntity> {
        return data.toTopRatedEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getTopRatedAnime(page)
    }

}
