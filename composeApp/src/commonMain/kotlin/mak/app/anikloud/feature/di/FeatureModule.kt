package mak.app.anikloud.feature.di

import mak.app.anikloud.data.di.DataQualifiers
import mak.app.anikloud.feature.discover.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
//    viewModelOf(::DiscoverViewModel)
    viewModel {
        DiscoverViewModel(
            refreshAiringAnimeUseCase = get(DataQualifiers.REFRESH_AIRING),
            getAiringAnimeUseCase = get(DataQualifiers.GET_AIRING),
            dispatcher = get()
        )
    }
}