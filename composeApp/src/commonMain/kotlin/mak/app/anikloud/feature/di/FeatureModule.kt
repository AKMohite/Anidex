package mak.app.anikloud.feature.di

import mak.app.anikloud.data.di.DataQualifiers.GET_AIRING
import mak.app.anikloud.data.di.DataQualifiers.GET_BANNER
import mak.app.anikloud.data.di.DataQualifiers.GET_TRENDING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_AIRING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_TRENDING
import mak.app.anikloud.feature.discover.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
//    viewModelOf(::DiscoverViewModel)
    viewModel {
        DiscoverViewModel(
            refreshAiringAnimeUseCase = get(REFRESH_AIRING),
            refreshTrendingAnimeUseCase = get(REFRESH_TRENDING),
            getAiringAnimeUseCase = get(GET_AIRING),
            getTrendingAnimeUseCase = get(GET_TRENDING),
            getBannerUseCase = get(GET_BANNER),
            dispatcher = get()
        )
    }
}