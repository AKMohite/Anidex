package mak.app.anikloud.feature.discover

import mak.app.anikloud.core.common.ui.UiText
import mak.app.anikloud.domain.model.Anime

internal data class DiscoverState(
    val upcomingAnime: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val selectedAnime: Int? = null,
    val errorMessage: UiText? = null
)