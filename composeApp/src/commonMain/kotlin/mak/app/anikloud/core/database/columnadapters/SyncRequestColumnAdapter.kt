package mak.app.anikloud.core.database.columnadapters

import app.cash.sqldelight.ColumnAdapter
import mak.app.anikloud.domain.model.SyncRequest

internal object SyncRequestColumnAdapter: ColumnAdapter<SyncRequest, String> {
    override fun decode(databaseValue: String): SyncRequest {
        return SyncRequest.entries.first { it.name == databaseValue }
    }

    override fun encode(value: SyncRequest): String = value.name

}