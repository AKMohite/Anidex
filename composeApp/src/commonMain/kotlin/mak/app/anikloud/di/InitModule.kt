package mak.app.anikloud.di

import mak.app.anikloud.core.database.di.databaseModule
import mak.app.anikloud.core.remote.di.remoteModule
import mak.app.anikloud.data.di.dataModule
import mak.app.anikloud.feature.di.featureModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin{
        config?.invoke(this)
        modules(platformModule, remoteModule, databaseModule, dataModule, featureModule)
    }
}