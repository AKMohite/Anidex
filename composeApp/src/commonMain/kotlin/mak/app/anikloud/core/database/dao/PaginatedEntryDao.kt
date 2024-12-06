package mak.app.anikloud.core.database.dao

import kotlinx.coroutines.flow.Flow

internal interface PaginatedEntryDao<Entity>: BaseDAO<Entity> {
    fun deletePage(page: Int)
//    fun getLastPage(): Int?

    fun updatePage(
    page: Int,
    entities: List<Entity>
    ) {
        deletePage(page)
        insert(entities)
    }
}

internal interface EntityDAO<Entity>: PaginatedEntryDao<Entity> {
    fun entriesObservable(page: Int): Flow<List<AnimeEntity>>
}
