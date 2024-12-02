package mak.app.anikloud.feature.discover

import mak.app.anikloud.core.common.ui.UiText
import mak.app.anikloud.domain.model.Anime

internal data class DiscoverState(
    val airingAnime: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val selectedAnime: Long? = null,
    val errorMessage: UiText? = null
)