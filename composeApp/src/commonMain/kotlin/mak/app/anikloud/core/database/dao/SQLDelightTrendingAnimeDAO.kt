package mak.app.anikloud.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_DATA_LIMIT
import mak.app.anikloud.local.Trending_animes


internal typealias TrendingAnimeEntity = Trending_animes

internal class SQLDelightTrendingAnimeDAO(
    db: AnikloudDatabase,
    private val dispatcher: Dispatcher
): TrendingAnimeDAO {
    private val query = db.trending_anime_entityQueries

    override fun entriesObservable(page: Int): Flow<List<AnimeEntity>> {
        val offset = (page - 1) * QUERY_DATA_LIMIT
        return query.getTrendingAnimes(limit = QUERY_DATA_LIMIT.toLong(), offset = offset.toLong())
            .asFlow()
            .mapToList(dispatcher.io)
    }

    override fun deletePage(page: Int) {
        query.deletePage(page)
    }

    override fun insert(entity: TrendingAnimeEntity) {
        query.insertAnime(
            id = entity.id,
            anime_id = entity.anime_id,
            page = entity.page
        )
    }

    override fun insert(entities: List<TrendingAnimeEntity>) {
        for (chunks in entities.chunked(QUERY_DATA_LIMIT)) {
            chunkInsert(chunks)
        }
    }

    private fun chunkInsert(chunks: List<TrendingAnimeEntity>) {
        for (entity in chunks) {
            insert(entity)
        }
    }

    override fun update(entity: TrendingAnimeEntity) {
        query.update(animeId = entity.anime_id, page = entity.page, id = entity.id)
    }

    override fun delete(entity: TrendingAnimeEntity) {
        query.delete(entity.id)
    }

    override fun deleteAll() {
        query.deleteAll()
    }

}

internal interface TrendingAnimeDAO: EntityDAO<TrendingAnimeEntity>
