package mak.app.anikloud.data.di

import org.koin.core.qualifier.named

internal object DataQualifiers {
    // region store
    val AIRING_STORE = named("koin-airing-store")
    // endregion

    // region get usecase
    val GET_BANNER = named("koin-get-banner")
    val GET_AIRING = named("koin-get-airing")
    // endregion

    // region refresh usecase
    val REFRESH_AIRING = named("koin-refresh-airing")
    // endregion
}