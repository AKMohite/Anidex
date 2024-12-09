package mak.app.anikloud

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mak.app.anikloud.core.common.designsystem.AdaptiveLayoutSize
import mak.app.anikloud.core.common.designsystem.AppTheme
import mak.app.anikloud.core.common.designsystem.DeviceWindowSize
import mak.app.anikloud.core.common.ui.navigation.AppBottomBar
import mak.app.anikloud.core.common.ui.navigation.AppDestination
import mak.app.anikloud.core.common.ui.navigation.AppNavGraph

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
            AnimatedVisibility(listOf(AdaptiveLayoutSize.Medium, AdaptiveLayoutSize.Expanded).contains(
                DeviceWindowSize.current)) {
                Text("Side bar")
            }
            AppNavGraph(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController
            )
        }
    }
}
