package mak.app.anikloud.data.di

import mak.app.anikloud.AppSekret.ENCRYPTION_KEY
import mak.app.anikloud.Sekret
import mak.app.anikloud.core.common.model.AppVault
import mak.app.anikloud.data.repository.AppAnimeRepository
import mak.app.anikloud.domain.repository.AnimeRepository
import org.koin.dsl.module

val dataModule = module {
    single<AnimeRepository> { AppAnimeRepository(api = get()) }
    single<AppVault> {
        if (isNativeLibLoaded()) {
            AppVault.loadSecrets(
                apiHost = Sekret.apiHost(ENCRYPTION_KEY)!!, // todo hide key this key is same as used in build.gradle
                baseUrlExt = Sekret.apiBaseExt(ENCRYPTION_KEY)!!,
                apiKey = Sekret.apiKey(ENCRYPTION_KEY)!!,
                contentType = Sekret.contentType(ENCRYPTION_KEY)!!,
                clientId = Sekret.clientId(ENCRYPTION_KEY)!!,
                clientSecret = Sekret.clientSecret(ENCRYPTION_KEY)!!
            )
        } else {
            AppVault
        }
    }
}

expect fun isNativeLibLoaded(): Boolean