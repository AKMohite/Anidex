package mak.app.anikloud.core.common.ui.navigation

import kotlinx.serialization.Serializable
import mak.app.anikloud.domain.model.DiscoverCategory

internal sealed interface AppRoute {
    @Serializable
    data object Discover: AppRoute
    @Serializable
    data object Search: AppRoute
    @Serializable
    data object Watchlist: AppRoute
    @Serializable
    data object Settings: AppRoute
    @Serializable
    data class AnimeCategory(private val type: DiscoverCategory)
    @Serializable
    data class AnimeDetail(private val animeId: Int): AppRoute
    @Serializable
    data class Episodes(private val animeId: Int): AppRoute
}