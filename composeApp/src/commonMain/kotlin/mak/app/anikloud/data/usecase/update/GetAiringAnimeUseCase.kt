package mak.app.anikloud.data.usecase.update

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import mak.app.anikloud.core.database.dao.AiringAnimeDAO
import mak.app.anikloud.data.toModels
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.GetAnimeUsecase

internal class GetAiringAnimeUseCase(
    private val airingAnimeDAO: AiringAnimeDAO
): GetAnimeUsecase<Int, Flow<List<Anime>>> {
    override fun invoke(params: Int): Flow<List<Anime>> {
        return airingAnimeDAO.entriesObservable(params)
            .distinctUntilChanged()
            .map { it.toModels() }
    }
}