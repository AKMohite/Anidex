package mak.app.anikloud.core.database.di

import app.cash.sqldelight.db.SqlDriver
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.OtakuDatabase
import mak.app.anikloud.core.database.SQLDatabaseTransactionRunner
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.SQLDelightAnimeDAO
import org.koin.dsl.module

internal val databaseModule = module {
    single<AnikloudDatabase> { OtakuDatabase(get<SqlDriver>()).build() }
    single<DatabaseTransactionRunner> { SQLDatabaseTransactionRunner(get()) }
    single<AnimeDAO> { SQLDelightAnimeDAO(db = get()) }
}