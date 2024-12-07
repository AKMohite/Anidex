package mak.app.anikloud.feature.di

import mak.app.anikloud.data.di.DataQualifiers.GET_AIRING
import mak.app.anikloud.data.di.DataQualifiers.GET_BANNER
import mak.app.anikloud.data.di.DataQualifiers.GET_POPULAR
import mak.app.anikloud.data.di.DataQualifiers.GET_TOP_RATED
import mak.app.anikloud.data.di.DataQualifiers.GET_TRENDING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_AIRING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_POPULAR
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_TOP_RATED
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
            refreshPopularAnimeUseCase = get<RefreshAnimeUsecase>(REFRESH_POPULAR),
            refreshTopRatedAnimeUseCase = get<RefreshAnimeUsecase>(REFRESH_TOP_RATED),
            refreshTrendingAnimeUseCase = get<RefreshAnimeUsecase>(REFRESH_TRENDING),
            getAiringAnimeUseCase = get<GetAnimeUsecase>(GET_AIRING),
            getTrendingAnimeUseCase = get<GetAnimeUsecase>(GET_TRENDING),
            getPopularAnimeUseCase = get<GetAnimeUsecase>(GET_POPULAR),
            getTopRatedAnimeUseCase = get<GetAnimeUsecase>(GET_TOP_RATED),
            getBannerUseCase = get<GetAnimeUsecase>(GET_BANNER),
            dispatcher = get()
        )
    }
}