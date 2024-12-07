package mak.app.anikloud.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_DATA_LIMIT
import mak.app.anikloud.local.Airing_animes

internal typealias AiringAnimeEntity = Airing_animes

internal class SQLDelightAiringAnimeDAO(
    db: AnikloudDatabase,
    private val dispatcher: Dispatcher
): AiringAnimeDAO {

    private val query = db.airing_anime_entityQueries

    override fun insert(entities: List<AiringAnimeEntity>) {
        for (chunks in entities.chunked(QUERY_DATA_LIMIT)) {
            chunkInsert(chunks)
        }
    }

    private fun chunkInsert(chunks: List<AiringAnimeEntity>) {
        for (entity in chunks) {
            insert(entity)
        }
    }

    override fun update(entity: AiringAnimeEntity) {
        query.update(animeId = entity.anime_id, page = entity.page, id = entity.id)
    }

    override fun insert(entity: AiringAnimeEntity) {
        query.insertAnime(id = entity.id, anime_id = entity.anime_id, page = entity.page)
    }

    override fun entriesObservable(page: Int): Flow<List<AnimeEntity>> {
        return query.entriesInPage(page)
            .asFlow()
            .mapToList(dispatcher.io)
    }

    override fun deletePage(page: Int) {
        query.deletePage(page)
    }

    override fun delete(entity: AiringAnimeEntity) {
        query.delete(entity.id)
    }

    override fun deleteAll() {
        query.deleteAll()
    }

}

internal interface AiringAnimeDAO: EntityDAO<AiringAnimeEntity>