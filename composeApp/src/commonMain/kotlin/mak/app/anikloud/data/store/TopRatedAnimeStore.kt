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
    override val animeDAO: AnimeDAO,
    override val tableDAO: TopRatedAnimeDAO,
    override val lastSyncDao: LastSyncDAO,
    override val transactionRunner: DatabaseTransactionRunner,
    override val dispatcher: Dispatcher,
    override val syncRequest: SyncRequest = SyncRequest.TOP_RATED_ANIME
): OfflineAnimeStore<TopRatedAnimeEntity>() {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<TopRatedAnimeEntity> {
        return data.toTopRatedEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getTopRatedAnime(page)
    }

}
