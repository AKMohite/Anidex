package mak.app.anikloud.core.common.ui.navigation

import anikloud.composeapp.generated.resources.Res
import anikloud.composeapp.generated.resources.icon_discover
import anikloud.composeapp.generated.resources.icon_search
import anikloud.composeapp.generated.resources.icon_settings
import anikloud.composeapp.generated.resources.icon_watch_list
import anikloud.composeapp.generated.resources.nav_bar_discover_title
import anikloud.composeapp.generated.resources.nav_bar_search_title
import anikloud.composeapp.generated.resources.nav_bar_settings_title
import anikloud.composeapp.generated.resources.nav_bar_watch_list_title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal sealed class AppDestination(
    val path: String,
    val iconRes: DrawableResource,
    val textRes: StringResource
) {

    data object DiscoverDestination: AppDestination(
        path = "discover",
        iconRes= Res.drawable.icon_discover,
        textRes = Res.string.nav_bar_discover_title
    )

    data object SearchDestination: AppDestination(
        path = "search",
        iconRes= Res.drawable.icon_search,
        textRes = Res.string.nav_bar_search_title
    )

    data object WatchlistDestination: AppDestination(
        path = "watchlist",
        iconRes= Res.drawable.icon_watch_list,
        textRes = Res.string.nav_bar_watch_list_title
    )

    data object SettingsDestination: AppDestination(
        path = "settings",
        iconRes= Res.drawable.icon_settings,
        textRes = Res.string.nav_bar_settings_title
    )
}
