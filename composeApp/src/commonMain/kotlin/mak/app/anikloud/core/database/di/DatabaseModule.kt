package mak.app.anikloud.core.database.di

import app.cash.sqldelight.db.SqlDriver
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.OtakuDatabase
import mak.app.anikloud.core.database.SQLDatabaseTransactionRunner
import mak.app.anikloud.core.database.SQLDelightLastSyncDAO
import mak.app.anikloud.core.database.dao.AiringAnimeDAO
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.TopRatedAnimeDAO
import mak.app.anikloud.core.database.dao.PopularAnimeDAO
import mak.app.anikloud.core.database.dao.SQLDelightAiringAnimeDAO
import mak.app.anikloud.core.database.dao.SQLDelightAnimeDAO
import mak.app.anikloud.core.database.dao.SQLDelightTopRatedAnimeDAO
import mak.app.anikloud.core.database.dao.SQLDelightPopularAnimeDAO
import mak.app.anikloud.core.database.dao.SQLDelightTrendingAnimeDAO
import mak.app.anikloud.core.database.dao.TrendingAnimeDAO
import org.koin.dsl.module

internal val databaseModule = module {
    single<AnikloudDatabase> { OtakuDatabase(get<SqlDriver>()).build() }
    single<DatabaseTransactionRunner> { SQLDatabaseTransactionRunner(get()) }
    single<AnimeDAO> { SQLDelightAnimeDAO(db = get()) }
    single<AiringAnimeDAO> { SQLDelightAiringAnimeDAO(db = get(), dispatcher = get())  }
    single<PopularAnimeDAO> { SQLDelightPopularAnimeDAO(db = get(), dispatcher = get())  }
    single<TopRatedAnimeDAO> { SQLDelightTopRatedAnimeDAO(db = get(), dispatcher = get())  }
    single<TrendingAnimeDAO> { SQLDelightTrendingAnimeDAO(db = get(), dispatcher = get())  }
    single<LastSyncDAO> { SQLDelightLastSyncDAO(db = get(), dispatcher = get())  }
}