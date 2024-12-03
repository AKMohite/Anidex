package mak.app.anikloud.core.database

import app.cash.sqldelight.db.SqlDriver
import mak.app.anikloud.AnikloudDatabase

internal class OtakuDatabase(
    private val driver: SqlDriver
) {
    fun build(): AnikloudDatabase {
        return AnikloudDatabase(
            driver = driver
        )
    }
}
