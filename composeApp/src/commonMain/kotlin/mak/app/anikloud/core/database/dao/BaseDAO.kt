package mak.app.anikloud.core.database.dao

internal interface BaseDAO<T> {
    fun insert(entity: T)
    fun insert(entities: List<T>)
    fun update(entity: T)
    fun delete(entity: T)
    fun deleteAll()
}
