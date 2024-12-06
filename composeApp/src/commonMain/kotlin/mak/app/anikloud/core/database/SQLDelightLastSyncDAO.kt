package mak.app.anikloud.core.database

import kotlinx.datetime.Clock
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.SQLDelightLastSyncDAO.Companion.DEFAULT_ID
import mak.app.anikloud.domain.model.SyncRequest
import mak.app.anikloud.local.Last_syncs
import kotlin.time.Duration

internal typealias LastSyncEntity = Last_syncs

internal class SQLDelightLastSyncDAO(
    db: AnikloudDatabase,
    private val dispatcher: Dispatcher
): LastSyncDAO {

    private val query = db.last_sync_entityQueries

    override fun insert(requestType: SyncRequest, entityId: String) {
        query.insert(
            id = null,
            requestType = requestType,
            entityId = entityId,
            timestamp = Clock.System.now()
        )
    }

    override suspend fun isRequestValid(
        requestType: SyncRequest,
        entityId: String,
        threshold: Duration
    ): Boolean {
        val lastSync = getLastSyncFor(requestType, entityId) ?: return false
        val requestBefore = Clock.System.now() - threshold
        return lastSync.timestamp > requestBefore
    }

    override suspend fun getLastSyncFor(requestType: SyncRequest, entityId: String): LastSyncEntity? /*= withContext(dispatcher.io)*/ {
        return query.getLastSyncFor(requestType, entityId).executeAsOneOrNull()
    }

    companion object {
        const val DEFAULT_ID = "N/A"
    }

}

internal interface LastSyncDAO {
    suspend fun getLastSyncFor(requestType: SyncRequest, entityId: String = DEFAULT_ID): LastSyncEntity?
    fun insert(requestType: SyncRequest, entityId: String = DEFAULT_ID)
    suspend fun isRequestValid(requestType: SyncRequest, entityId: String = DEFAULT_ID, threshold: Duration): Boolean

}
