package mak.app.anikloud.core.database.di

import app.cash.sqldelight.db.SqlDriver
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.database.OtakuDatabase
import org.koin.dsl.module

internal val databaseModule = module {
    single<AnikloudDatabase> { OtakuDatabase(get<SqlDriver>()).build() }
//    single<DatabaseTransactionRunner> { SQLDatabaseTransactionRunner(get()) }
//    single<AnimeDAO> { SQLDelightAnimeDAO() }
}