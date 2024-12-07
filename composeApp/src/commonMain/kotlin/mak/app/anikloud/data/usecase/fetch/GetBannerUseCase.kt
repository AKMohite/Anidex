package mak.app.anikloud.data.usecase.fetch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import mak.app.anikloud.core.database.dao.AiringAnimeDAO
import mak.app.anikloud.data.toModels
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.GetAnimeUsecase

/**
 * this is just 10 airing anime for showcasing pager
 * just fancy stuff
 */
internal class GetBannerUseCase(
    private val airingAnimeDAO: AiringAnimeDAO
): GetAnimeUsecase<Int, Flow<List<Anime>>> {
    override fun invoke(params: Int): Flow<List<Anime>> {
        return airingAnimeDAO.entriesObservable(params)
            .distinctUntilChanged()
//            .take(14)
            .map { it.toModels().take(6).shuffled() }
    }
}