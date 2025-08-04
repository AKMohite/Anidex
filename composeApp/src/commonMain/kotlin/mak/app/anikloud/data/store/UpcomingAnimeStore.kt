package mak.app.anikloud.data.store

import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.UpcomingAnimeDAO
import mak.app.anikloud.core.database.dao.UpcomingAnimeEntity
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.data.toUpcomingEntities
import mak.app.anikloud.domain.model.SyncRequest


internal class UpcomingAnimeStore(
    private val api: AnimeAPI,
    override val animeDAO: AnimeDAO,
    override val tableDAO: UpcomingAnimeDAO,
    override val lastSyncDao: LastSyncDAO,
    override val transactionRunner: DatabaseTransactionRunner,
    override val dispatcher: Dispatcher,
    override val syncRequest: SyncRequest = SyncRequest.UPCOMING_ANIME
): OfflineAnimeStore<UpcomingAnimeEntity>() {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<UpcomingAnimeEntity> {
        return data.toUpcomingEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getAnticipatedAnime(page)
    }

}
