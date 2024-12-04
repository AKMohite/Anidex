package mak.app.anikloud.di

import mak.app.anikloud.core.common.util.provideDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

internal val utilModule = module {
    factory { provideDispatcher() }
}

expect val platformModule: Module
