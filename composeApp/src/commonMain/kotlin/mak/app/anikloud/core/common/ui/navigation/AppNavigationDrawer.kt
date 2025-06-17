package mak.app.anikloud.core.common.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppNavigationDrawer(
    currentDestination: String,
    onDrawerItemClick: (AppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .padding(8.dp)
    ) {
        for (item in bottomNavItems) {
            NavigationDrawerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = stringResource(item.titleId),
                    )
                },
                label = { Text(text = stringResource(item.titleId)) },
                isSelected = currentDestination == item.screen.path,
                onClick = { onDrawerItemClick(item.screen) },
            )
        }
    }
}

@Composable
private fun NavigationDrawerItem(
    label: @Composable () -> Unit,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit),
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
    ) {
        icon()
        Spacer(Modifier.width(4.dp))
        label()

    }
}
