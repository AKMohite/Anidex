package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
//                .border(8.dp, Color.Red, CircleShape)
//                .widthIn(min = 80.dp)
                .padding(16.dp)
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