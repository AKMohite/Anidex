package mak.app.anikloud.feature.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mak.app.anikloud.feature.discover.components.AnimePager
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun DiscoverRoute(
    viewModel: DiscoverViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DiscoverScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
internal fun DiscoverScreen(
    state: DiscoverState,
    onAction: (DiscoverAction) -> Unit
) {
    AnimePager(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        animes = state.airingAnime
    )
}