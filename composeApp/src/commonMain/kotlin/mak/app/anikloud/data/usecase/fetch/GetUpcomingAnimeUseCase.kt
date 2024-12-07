package mak.app.anikloud.data.usecase.fetch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import mak.app.anikloud.core.database.dao.AiringAnimeDAO
import mak.app.anikloud.core.database.dao.PopularAnimeDAO
import mak.app.anikloud.core.database.dao.UpcomingAnimeDAO
import mak.app.anikloud.data.toModels
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.usecase.GetAnimeUsecase

internal class GetUpcomingAnimeUseCase(
    private val upcomingAnimeDAO: UpcomingAnimeDAO
): GetAnimeUsecase {
    override fun invoke(params: Int): Flow<List<Anime>> {
        return upcomingAnimeDAO.entriesObservable(params)
            .distinctUntilChanged()
            .map { it.toModels() }
    }
}