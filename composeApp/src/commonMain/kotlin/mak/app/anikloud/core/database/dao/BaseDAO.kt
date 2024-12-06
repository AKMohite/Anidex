package mak.app.anikloud.core.database.dao

internal interface BaseDAO<Entity> {
    fun insert(entity: Entity)
    fun insert(entities: List<Entity>)

    fun update(entity: Entity)
//    fun upsert(entity: Entity): Long = upsert(entity, ::insert, ::update)
    fun delete(entity: Entity)
    fun deleteAll()
}
