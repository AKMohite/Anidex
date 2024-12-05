package mak.app.anikloud.core.database

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import mak.app.anikloud.AnikloudDatabase
import mak.app.anikloud.core.database.columnadapters.InstantLongColumnAdapter
import mak.app.anikloud.core.database.columnadapters.SyncRequestColumnAdapter
import mak.app.anikloud.local.Airing_animes
import mak.app.anikloud.local.Last_syncs
import mak.app.anikloud.local.Popular_animes
import mak.app.anikloud.local.Top_animes
import mak.app.anikloud.local.Trending_animes
import mak.app.anikloud.local.Upcoming_animes

internal class OtakuDatabase(
    private val driver: SqlDriver
) {
    fun build(): AnikloudDatabase {
        return AnikloudDatabase(
            driver = driver,
            airing_animesAdapter = Airing_animes.Adapter(
                pageAdapter = IntColumnAdapter
            ),
            last_syncsAdapter = Last_syncs.Adapter(
                request_typeAdapter = SyncRequestColumnAdapter,
                timestampAdapter = InstantLongColumnAdapter
            ),
            popular_animesAdapter = Popular_animes.Adapter(
                pageAdapter = IntColumnAdapter
            ),
            top_animesAdapter = Top_animes.Adapter(
                pageAdapter = IntColumnAdapter
            ),
            trending_animesAdapter = Trending_animes.Adapter(
                pageAdapter = IntColumnAdapter
            ),
            upcoming_animesAdapter = Upcoming_animes.Adapter(
                pageAdapter = IntColumnAdapter
            )
        )
    }
}
