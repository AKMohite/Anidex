package mak.app.anikloud.data.usecase.refresh

import mak.app.anikloud.data.store.DiscoverAnimeStore
import mak.app.anikloud.data.store.fetch
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase

internal class RefreshTrendingAnimeUseCase(
    private val airingStore: DiscoverAnimeStore
): RefreshAnimeUsecase {
    override suspend fun invoke(params: Int): List<Anime> {
        return airingStore()
            .fetch(params, false)
    }
}