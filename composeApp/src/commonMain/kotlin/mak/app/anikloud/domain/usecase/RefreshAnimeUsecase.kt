package mak.app.anikloud.domain.usecase

import mak.app.anikloud.domain.model.Anime

internal interface RefreshAnimeUsecase: BaseUsecase<Int, List<Anime>>