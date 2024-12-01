package mak.app.anikloud.domain.repository

import mak.app.anikloud.domain.model.Anime

internal interface AnimeRepository {
    suspend fun getUpcomingAnime(): List<Anime>
}
