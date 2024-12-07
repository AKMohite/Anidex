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
    animeDAO: AnimeDAO,
    upcomingAnimeDAO: UpcomingAnimeDAO,
    lastSyncDao: LastSyncDAO,
    transactionRunner: DatabaseTransactionRunner,
    dispatcher: Dispatcher
): OfflineAnimeStore<UpcomingAnimeEntity>(
    animeDAO = animeDAO,
    tableDAO = upcomingAnimeDAO,
    lastSyncDao = lastSyncDao,
    transactionRunner = transactionRunner,
    dispatcher = dispatcher,
    syncRequest = SyncRequest.UPCOMING_ANIME
) {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<UpcomingAnimeEntity> {
        return data.toUpcomingEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getAnticipatedAnime(page)
    }

}
