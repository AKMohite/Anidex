package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mak.app.anikloud.core.common.ui.navigation.AppDestination.DiscoverDestination
import mak.app.anikloud.core.common.ui.navigation.AppDestination.SearchDestination
import mak.app.anikloud.core.common.ui.navigation.AppDestination.SettingsDestination
import mak.app.anikloud.core.common.ui.navigation.AppDestination.WatchlistDestination
import mak.app.anikloud.feature.discover.DiscoverRoute

@Composable
internal fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = DiscoverDestination.path
    ) {
        composable(DiscoverDestination.path) {
            DiscoverRoute()
        }

        composable(SearchDestination.path) {
            EmptyContent(SearchDestination)
        }

        composable(WatchlistDestination.path) { EmptyContent(WatchlistDestination) }

        composable(SettingsDestination.path) { EmptyContent(SettingsDestination) }

//        composable<AnimeDetail> { EmptyContent(AnimeDetail(0)) }
    }
}

@Composable
private fun EmptyContent(
    route: AppDestination,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxSize(),
        text = "in progress: ${route.path}"
    )
}

