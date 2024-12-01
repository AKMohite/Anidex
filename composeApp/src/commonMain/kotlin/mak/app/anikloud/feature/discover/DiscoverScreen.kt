package mak.app.anikloud.feature.discover

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    AnimePager(animes = state.airingAnime)
}