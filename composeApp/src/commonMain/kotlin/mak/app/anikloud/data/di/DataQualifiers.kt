package mak.app.anikloud.data.di

import org.koin.core.qualifier.named

internal object DataQualifiers {
    // region store
    val AIRING_STORE = named("koin-airing-store")
    val POPULAR_STORE = named("koin-popular-store")
    val TOP_RATED_STORE = named("koin-top-rated-store")
    val TRENDING_STORE = named("koin-trending-store")
    val UPCOMING_STORE = named("koin-upcoming-store")
    // endregion

    // region get usecase
    val GET_BANNER = named("koin-get-banner")
    val GET_AIRING = named("koin-get-airing")
    val GET_POPULAR = named("koin-get-popular")
    val GET_TOP_RATED = named("koin-get-top-rated")
    val GET_UPCOMING = named("koin-get-upcoming")
    val GET_TRENDING = named("koin-get-trending")
    // endregion

    // region refresh usecase
    val REFRESH_AIRING = named("koin-refresh-airing")
    val REFRESH_POPULAR = named("koin-refresh-popular")
    val REFRESH_UPCOMING = named("koin-refresh-upcoming")
    val REFRESH_TOP_RATED = named("koin-refresh-top-rated")
    val REFRESH_TRENDING = named("koin-refresh-trending")
    // endregion
}