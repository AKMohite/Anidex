package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Column(
        modifier = modifier
            .border(width = 2.dp, color = MaterialTheme.colorScheme.surfaceVariant, shape = CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
            .widthIn(min = 90.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
//        NavigationRail(
//            modifier = modifier
////            .border(width = 2.dp, color = MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.extraLarge)
////            .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = MaterialTheme.shapes.extraLarge)
////            .widthIn(min = 90.dp)
//                .padding(16.dp)
//        ) {
        Spacer(Modifier.height(24.dp))
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