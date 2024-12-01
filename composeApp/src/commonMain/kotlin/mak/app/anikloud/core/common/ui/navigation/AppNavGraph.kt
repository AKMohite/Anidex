package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mak.app.anikloud.feature.discover.DiscoverRoute

@Composable
internal fun AppNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "discover"
    ) {
        composable("discover") {
            DiscoverRoute()
        }
    }
}

