package mak.app.anikloud.data.store

import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.EntityDAO
import mak.app.anikloud.core.database.dao.PopularAnimeDAO
import mak.app.anikloud.core.database.dao.PopularAnimeEntity
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.data.toPopularEntities
import mak.app.anikloud.domain.model.SyncRequest


internal class PopularAnimeStore(
    private val api: AnimeAPI,
    override val animeDAO: AnimeDAO,
    override val tableDAO: PopularAnimeDAO,
    override val lastSyncDao: LastSyncDAO,
    override val transactionRunner: DatabaseTransactionRunner,
    override val dispatcher: Dispatcher,
    override val syncRequest: SyncRequest = SyncRequest.POPULAR_ANIME
): OfflineAnimeStore<PopularAnimeEntity>() {

    override fun mapEntities(page: Int, data: List<DataDTO>): List<PopularAnimeEntity> {
        return data.toPopularEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getPopularAnime(page)
    }

}
