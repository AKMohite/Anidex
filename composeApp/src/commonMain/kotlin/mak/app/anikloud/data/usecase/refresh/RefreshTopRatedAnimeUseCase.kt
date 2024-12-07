package mak.app.anikloud.data.usecase.refresh

import mak.app.anikloud.data.store.DiscoverAnimeStore
import mak.app.anikloud.data.store.fetch
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase

internal class RefreshTopRatedAnimeUseCase(
    private val topRated: DiscoverAnimeStore
): RefreshAnimeUsecase {
    override suspend fun invoke(params: Int): List<Anime> {
        return topRated()
            .fetch(params, false)
    }
}