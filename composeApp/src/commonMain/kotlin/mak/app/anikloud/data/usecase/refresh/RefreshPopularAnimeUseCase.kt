package mak.app.anikloud.data.usecase.refresh

import mak.app.anikloud.data.store.DiscoverAnimeStore
import mak.app.anikloud.data.store.fetch
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase

internal class RefreshPopularAnimeUseCase(
    private val popularStore: DiscoverAnimeStore
): RefreshAnimeUsecase {
    override suspend fun invoke(params: Int): List<Anime> {
        return popularStore()
            .fetch(params, false)
    }
}