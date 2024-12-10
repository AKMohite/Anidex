package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppNavigationRail(
    currentDestination: String,
    onNavRailItemClick: (AppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
//    Column(
//        modifier = modifier
//    ) {
        NavigationRail(
            modifier = modifier
        ) {
            for (item in bottomNavItems) {
                NavigationRailItem(
                    selected = currentDestination == item.screen.path,
                    onClick = {
                        onNavRailItemClick(item.screen)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.iconId),
                            contentDescription = stringResource(item.titleId)
                        )
                    },
                    label = {
                        Text(stringResource(item.titleId))
                    }
                )
            }
        }
//    }
}