package mak.app.anikloud.data.store

import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.PopularAnimeDAO
import mak.app.anikloud.core.database.dao.PopularAnimeEntity
import mak.app.anikloud.core.database.dao.TrendingAnimeDAO
import mak.app.anikloud.core.database.dao.TrendingAnimeEntity
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.core.remote.ext.retry
import mak.app.anikloud.data.toPopularEntities
import mak.app.anikloud.data.toTrendingEntities
import mak.app.anikloud.domain.model.SyncRequest


internal class PopularAnimeStore(
    private val api: AnimeAPI,
    animeDAO: AnimeDAO,
    popularAnimeDAO: PopularAnimeDAO,
    lastSyncDao: LastSyncDAO,
    transactionRunner: DatabaseTransactionRunner,
    dispatcher: Dispatcher
): OfflineAnimeStore<PopularAnimeEntity>(
    animeDAO = animeDAO,
    tableDAO = popularAnimeDAO,
    lastSyncDao = lastSyncDao,
    transactionRunner = transactionRunner,
    dispatcher = dispatcher,
    syncRequest = SyncRequest.POPULAR_ANIME
) {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<PopularAnimeEntity> {
        return data.toPopularEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.popularAnime(page)
    }

}
