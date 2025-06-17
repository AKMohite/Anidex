package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import anikloud.composeapp.generated.resources.Res
import anikloud.composeapp.generated.resources.icon_discover
import anikloud.composeapp.generated.resources.icon_search
import anikloud.composeapp.generated.resources.icon_settings
import anikloud.composeapp.generated.resources.icon_watch_list
import anikloud.composeapp.generated.resources.nav_bar_discover_title
import anikloud.composeapp.generated.resources.nav_bar_search_title
import anikloud.composeapp.generated.resources.nav_bar_settings_title
import anikloud.composeapp.generated.resources.nav_bar_watch_list_title
import mak.app.anikloud.core.common.designsystem.AdaptiveLayoutSize
import mak.app.anikloud.core.common.designsystem.DeviceWindowSize
import mak.app.anikloud.core.common.ui.navigation.AppDestination.DiscoverDestination
import mak.app.anikloud.core.common.ui.navigation.AppDestination.SearchDestination
import mak.app.anikloud.core.common.ui.navigation.AppDestination.SettingsDestination
import mak.app.anikloud.core.common.ui.navigation.AppDestination.WatchlistDestination
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppBottomBar(
    currentScreen: String,
    onBottomNavClick: (AppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    val isBottomBarDisplayed = bottomNavItems.any { it.screen.path == currentScreen }
    AnimatedVisibility(
        visible = DeviceWindowSize.current == AdaptiveLayoutSize.Compact && isBottomBarDisplayed,
        enter = slideInVertically(
            animationSpec = tween(300),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(300),
            targetOffsetY = { it }
        )
    ) {
        AppBottomNavigation(
            currentDestination = currentScreen,
            onBottomNavClick = onBottomNavClick,
            modifier = modifier
        )
    }
}

@Composable
private fun AppBottomNavigation(
    currentDestination: String,
    onBottomNavClick: (AppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = Modifier
    ) {
        for (item in bottomNavItems) {
            val isSelected = currentDestination == item.screen.path
            NavigationBarItem(
                label = {
                    Text(text = stringResource(item.titleId))
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = stringResource(item.titleId)
                    )
                },
                selected = isSelected,
                onClick = {
                    onBottomNavClick(item.screen)
                }
            )
        }
    }
}

internal val bottomNavItems = listOf(
    BottomNavItem(
        iconId = Res.drawable.icon_discover,
        titleId = Res.string.nav_bar_discover_title,
        screen = DiscoverDestination,
    ),
    BottomNavItem(
        iconId = Res.drawable.icon_search,
        titleId = Res.string.nav_bar_search_title,
        screen = SearchDestination,
    ),
    BottomNavItem(
        iconId = Res.drawable.icon_watch_list,
        titleId = Res.string.nav_bar_watch_list_title,
        screen = WatchlistDestination,
    ),
    BottomNavItem(
        iconId = Res.drawable.icon_settings,
        titleId = Res.string.nav_bar_settings_title,
        screen = SettingsDestination,
    )
)

internal data class BottomNavItem(
    val iconId: DrawableResource,
    val titleId: StringResource,
    val screen: AppDestination
)