package mak.app.anikloud.feature.di

import mak.app.anikloud.data.di.DataQualifiers.GET_AIRING
import mak.app.anikloud.data.di.DataQualifiers.GET_BANNER
import mak.app.anikloud.data.di.DataQualifiers.GET_TRENDING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_AIRING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_TRENDING
import mak.app.anikloud.domain.usecase.GetAnimeUsecase
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase
import mak.app.anikloud.feature.discover.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
//    viewModelOf(::DiscoverViewModel)
    viewModel {
        DiscoverViewModel(
            refreshAiringAnimeUseCase = get<RefreshAnimeUsecase>(REFRESH_AIRING),
            refreshTrendingAnimeUseCase = get<RefreshAnimeUsecase>(REFRESH_TRENDING),
            getAiringAnimeUseCase = get<GetAnimeUsecase>(GET_AIRING),
            getTrendingAnimeUseCase = get<GetAnimeUsecase>(GET_TRENDING),
            getBannerUseCase = get<GetAnimeUsecase>(GET_BANNER),
            dispatcher = get()
        )
    }
}