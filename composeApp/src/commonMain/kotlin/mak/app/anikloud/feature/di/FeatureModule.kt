package mak.app.anikloud.feature.di

import mak.app.anikloud.data.di.DataQualifiers
import mak.app.anikloud.feature.discover.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
//    viewModelOf(::DiscoverViewModel)
    viewModel {
        DiscoverViewModel(get(DataQualifiers.REFRESH_AIRING), get(DataQualifiers.GET_AIRING))
    }
}