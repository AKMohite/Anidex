package mak.app.anikloud.data.usecase.refresh

import mak.app.anikloud.data.store.DiscoverAnimeStore
import mak.app.anikloud.data.store.fetch
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase

internal class RefreshAiringAnimeUseCase(
    private val airingStore: DiscoverAnimeStore
): RefreshAnimeUsecase {
    override suspend fun invoke(params: Int): List<Anime> {
        return airingStore()
            .fetch(params, false)
    }
}

data class PaginatedParams(
    val page: Int = 1,
    val isForceRefresh: Boolean = false
)