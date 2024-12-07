package mak.app.anikloud.domain.usecase

import kotlinx.coroutines.flow.Flow
import mak.app.anikloud.domain.model.Anime

internal interface GetAnimeUsecase {
    operator fun invoke(params: Int): Flow<List<Anime>>
}