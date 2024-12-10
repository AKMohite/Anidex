package mak.app.anikloud

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mak.app.anikloud.core.common.designsystem.AdaptiveLayoutSize
import mak.app.anikloud.core.common.designsystem.AppTheme
import mak.app.anikloud.core.common.designsystem.DeviceWindowSize
import mak.app.anikloud.core.common.ui.navigation.AppBottomBar
import mak.app.anikloud.core.common.ui.navigation.AppDestination
import mak.app.anikloud.core.common.ui.navigation.AppNavGraph
import mak.app.anikloud.core.common.ui.navigation.AppNavigationDrawer
import mak.app.anikloud.core.common.ui.navigation.AppNavigationRail

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
//            Box(
//                modifier = Modifier
//                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
//                    .clip(
//                        RoundedCornerShape(16.dp)
//                    )
////                    .background(bottomNavBarBgColor)
//            ) {
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
//            }
        },
        modifier = Modifier
//            .background(color = bottomNavBarBgColor)
            .systemBarsPadding()
    ) { paddingValues ->
        Row {
            AnimatedVisibility(DeviceWindowSize.current == AdaptiveLayoutSize.Medium) {
                AppNavigationRail(
                    currentDestination = currentDestination,
                    onNavRailItemClick = { route ->
                        navController.navigate(route.path) {
                            popUpTo(AppDestination.DiscoverDestination.path) {
                                inclusive = AppDestination.DiscoverDestination.path == route.path
                            }
                        }
                    }
                )
            }
            AnimatedVisibility(DeviceWindowSize.current == AdaptiveLayoutSize.Expanded) {
                AppNavigationDrawer(
                    modifier = Modifier.fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    currentDestination = currentDestination,
                    onDrawerItemClick = { route ->
                        navController.navigate(route.path) {
                            popUpTo(AppDestination.DiscoverDestination.path) {
                                inclusive = AppDestination.DiscoverDestination.path == route.path
                            }
                        }
                    }
                )
            }
            Spacer(Modifier.width(4.dp))
            AppNavGraph(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController
            )
        }
    }
}

