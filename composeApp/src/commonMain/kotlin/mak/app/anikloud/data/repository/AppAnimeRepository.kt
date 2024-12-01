package mak.app.anikloud.data.repository

import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.repository.AnimeRepository

internal class AppAnimeRepository(
    private val api: AnimeAPI
) : AnimeRepository {
    override suspend fun getUpcomingAnime(): List<Anime> {
        TODO("Not yet implemented")
    }
}