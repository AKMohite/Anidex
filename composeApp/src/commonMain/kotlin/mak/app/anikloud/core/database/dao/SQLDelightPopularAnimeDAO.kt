package mak.app.anikloud.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_DATA_LIMIT
import mak.app.anikloud.local.Popular_animes


internal typealias PopularAnimeEntity = Popular_animes

internal class SQLDelightPopularAnimeDAO(
    db: AnikloudDatabase,
    private val dispatcher: Dispatcher
): PopularAnimeDAO {
    private val query = db.popular_anime_entityQueries

    override fun entriesObservable(page: Int): Flow<List<AnimeEntity>> {
        return query.entriesInPage(page)
            .asFlow()
            .mapToList(dispatcher.io)
    }

    override fun deletePage(page: Int) {
        query.deletePage(page)
    }

    override fun insert(entity: PopularAnimeEntity) {
        query.insertAnime(
            id = entity.id,
            anime_id = entity.anime_id,
            page = entity.page
        )
    }

    override fun insert(entities: List<PopularAnimeEntity>) {
        for (chunks in entities.chunked(QUERY_DATA_LIMIT)) {
            chunkInsert(chunks)
        }
    }

    private fun chunkInsert(chunks: List<PopularAnimeEntity>) {
        for (entity in chunks) {
            insert(entity)
        }
    }

    override fun update(entity: PopularAnimeEntity) {
        query.update(animeId = entity.anime_id, page = entity.page, id = entity.id)
    }

    override fun delete(entity: PopularAnimeEntity) {
        query.delete(entity.id)
    }

    override fun deleteAll() {
        query.deleteAll()
    }

}

internal interface PopularAnimeDAO: EntityDAO<PopularAnimeEntity>
