package mak.app.anikloud.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_DATA_LIMIT
import mak.app.anikloud.local.Top_animes


internal typealias TopRatedAnimeEntity = Top_animes

internal class SQLDelightTopRatedAnimeDAO(
    db: AnikloudDatabase,
    private val dispatcher: Dispatcher
): TopRatedAnimeDAO {
    private val query = db.highest_rated_anime_entityQueries

    override fun entriesObservable(page: Int): Flow<List<AnimeEntity>> {
        return query.entriesInPage(page)
            .asFlow()
            .mapToList(dispatcher.io)
    }

    override fun deletePage(page: Int) {
        query.deletePage(page)
    }

    override fun insert(entity: TopRatedAnimeEntity) {
        query.insertAnime(
            id = entity.id,
            anime_id = entity.anime_id,
            page = entity.page
        )
    }

    override fun insert(entities: List<TopRatedAnimeEntity>) {
        for (chunks in entities.chunked(QUERY_DATA_LIMIT)) {
            chunkInsert(chunks)
        }
    }

    private fun chunkInsert(chunks: List<TopRatedAnimeEntity>) {
        for (entity in chunks) {
            insert(entity)
        }
    }

    override fun update(entity: TopRatedAnimeEntity) {
        query.update(animeId = entity.anime_id, page = entity.page, id = entity.id)
    }

    override fun delete(entity: TopRatedAnimeEntity) {
        query.delete(entity.id)
    }

    override fun deleteAll() {
        query.deleteAll()
    }

}

internal interface TopRatedAnimeDAO: EntityDAO<TopRatedAnimeEntity>
