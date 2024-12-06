package mak.app.anikloud.core.database.dao

import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.remote.utils.APIConstants.QUERY_DATA_LIMIT
import mak.app.anikloud.local.Animes

internal typealias AnimeEntity = Animes

internal class SQLDelightAnimeDAO(
    db: AnikloudDatabase
): AnimeDAO {

    private val query = db.anime_entityQueries

    override fun insert(entity: AnimeEntity) {
        query.insertPodcast(entity)
    }

    override fun insert(entities: List<AnimeEntity>) {
        for (chunks in entities.chunked(QUERY_DATA_LIMIT)) {
            chunkInsert(chunks)
        }
    }

    private fun chunkInsert(chunks: List<AnimeEntity>) {
        for (entity in chunks) {
            insert(entity)
        }
    }

    override fun update(entity: AnimeEntity) {

    }

    override fun delete(entity: AnimeEntity) {
        query.delete(entity.id)
    }

    override fun deleteAll() {
        query.deleteAll()
    }


}

internal interface AnimeDAO: BaseDAO<AnimeEntity> {
}
