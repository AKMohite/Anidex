package mak.app.anikloud.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import mak.app.anikloud.AnikloudDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AnikloudDatabase.Schema, "otakupedia.db")
    }
}