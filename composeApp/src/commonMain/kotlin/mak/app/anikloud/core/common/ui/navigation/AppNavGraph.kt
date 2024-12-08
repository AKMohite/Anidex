package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
        composable("anime-detail") {
            Text(
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxSize(),
                text = "Anime details for specific anime"
            )
        }
    }
}

