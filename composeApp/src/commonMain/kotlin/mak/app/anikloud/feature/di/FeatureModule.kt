package mak.app.anikloud.feature.di

import mak.app.anikloud.feature.discover.DiscoverViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    viewModelOf(::DiscoverViewModel)
}