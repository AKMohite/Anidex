package mak.app.anikloud.feature.discover

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mak.app.anikloud.domain.model.DiscoverCategory
import mak.app.anikloud.feature.discover.components.AnimeSection
import mak.app.anikloud.feature.discover.components.AnimePager
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun DiscoverRoute(
    viewModel: DiscoverViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DiscoverScreen(
        modifier = modifier
            .fillMaxSize(),
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
internal fun DiscoverScreen(
    state: DiscoverState,
    modifier: Modifier = Modifier,
    onAction: (DiscoverAction) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
//        item("Top Banner") {
//            val banners = state.categorisedAnime.firstOrNull { it.type == DiscoverCategory.BANNER }
//            if (banners != null && banners.animes.isNotEmpty()) {
//                AnimePager(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp),
//                    animes = banners.animes
//                )
//            }
//        }
        items(state.categorisedAnime, key = { section -> section.type.id }) { section ->
            if (section.type == DiscoverCategory.BANNER) {
                AnimePager(
                    modifier = Modifier
                        .fillMaxWidth(),
                    animes = section.animes,
                    onAnimeClick = { anime -> onAction(DiscoverAction.OnAnimeClick(anime)) }
                )
            } else {
                AnimeSection(
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    section = section
                )
            }
        }
    }
}
