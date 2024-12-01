package mak.app.anikloud.feature.discover

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mak.app.anikloud.domain.repository.AnimeRepository

internal class DiscoverViewModel(
    repository: AnimeRepository
): ViewModel() {

    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    fun onAction(action: DiscoverAction) {
        when(action) {
            is DiscoverAction.OnAnimeClick -> {
                _state.update {
                    it.copy(
                        selectedAnime = action.anime.id
                    )
                }
            }
        }
    }

}