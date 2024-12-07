package mak.app.anikloud.feature.di

import mak.app.anikloud.data.di.DataQualifiers
import mak.app.anikloud.data.di.DataQualifiers.GET_AIRING
import mak.app.anikloud.data.di.DataQualifiers.GET_BANNER
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_AIRING
import mak.app.anikloud.feature.discover.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
//    viewModelOf(::DiscoverViewModel)
    viewModel {
        DiscoverViewModel(
            refreshAiringAnimeUseCase = get(REFRESH_AIRING),
            getAiringAnimeUseCase = get(GET_AIRING),
            getBannerUseCase = get(GET_BANNER),
            dispatcher = get()
        )
    }
}