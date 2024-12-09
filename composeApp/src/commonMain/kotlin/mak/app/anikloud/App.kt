package mak.app.anikloud

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mak.app.anikloud.core.common.designsystem.AppTheme
import mak.app.anikloud.core.common.ui.navigation.AppBottomBar
import mak.app.anikloud.core.common.ui.navigation.AppNavGraph
import mak.app.anikloud.core.common.ui.navigation.AppDestination

@Composable
fun App() {
    AppTheme {
        Surface {
            RootContainer()
        }
    }
}

@Composable
internal fun RootContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: AppDestination.DiscoverDestination.path
    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentScreen = currentDestination,
                onBottomNavClick = { route ->
                    navController.navigate(route.path) {
                        popUpTo(AppDestination.DiscoverDestination.path) {
                            inclusive = AppDestination.DiscoverDestination.path == route.path
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        AppNavGraph(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController
        )
    }
}
