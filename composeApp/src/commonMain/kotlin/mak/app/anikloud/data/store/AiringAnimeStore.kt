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
    override val animeDAO: AnimeDAO,
    override val tableDAO: AiringAnimeDAO,
    override val lastSyncDao: LastSyncDAO,
    override val transactionRunner: DatabaseTransactionRunner,
    override val dispatcher: Dispatcher,
    override val syncRequest: SyncRequest = SyncRequest.AIRING_ANIME
): OfflineAnimeStore<AiringAnimeEntity>() {
    override fun mapEntities(page: Int, data: List<DataDTO>): List<AiringAnimeEntity> {
        return data.toAiringEntities(page)
    }

    override suspend fun apiCall(page: Int): DiscoverAnimeDTO {
        return api.getAiringAnime(page)
    }

}
