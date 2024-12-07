package mak.app.anikloud.data.store

import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.dao.AiringAnimeDAO
import mak.app.anikloud.core.database.dao.AiringAnimeEntity
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.data.toAiringEntities
import mak.app.anikloud.domain.model.SyncRequest


internal class AiringAnimeStore(
    private val api: AnimeAPI,
    animeDAO: AnimeDAO,
    airingAnimeDAO: AiringAnimeDAO,
    lastSyncDao: LastSyncDAO,
    transactionRunner: DatabaseTransactionRunner,
    dispatcher: Dispatcher
): OfflineAnimeStore<AiringAnimeEntity>(
    animeDAO = animeDAO,
    tableDAO = airingAnimeDAO,
    lastSyncDao = lastSyncDao,
    transactionRunner = transactionRunner,
    dispatcher = dispatcher,
    syncRequest = SyncRequest.AIRING_ANIME
) {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<AiringAnimeEntity> {
        return data.toAiringEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getAiringAnime(page)
    }

}
