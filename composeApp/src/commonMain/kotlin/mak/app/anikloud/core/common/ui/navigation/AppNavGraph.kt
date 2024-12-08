package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.utils.EmptyContent
import mak.app.anikloud.core.common.ui.navigation.AppRoute.*
import mak.app.anikloud.feature.discover.DiscoverRoute

@Composable
internal fun AppNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Discover
    ) {
        composable<Discover> {
            DiscoverRoute()
        }

        composable<Search> {
            EmptyContent(Search)
        }

        composable<Watchlist> { EmptyContent(Settings) }

        composable<Settings> { EmptyContent(Settings) }

        composable<AnimeDetail> { EmptyContent(AnimeDetail(0)) }
    }
}

@Composable
private fun EmptyContent(
    route: AppRoute,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxSize(),
        text = "in progress: $route"
    )
}

