package mak.app.anikloud.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import mak.app.anikloud.AnikloudDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AnikloudDatabase.Schema, context, "otakupedia.db")
    }
}