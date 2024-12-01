package mak.app.anikloud.feature.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mak.app.anikloud.core.common.model.onError
import mak.app.anikloud.core.common.model.onSuccess
import mak.app.anikloud.core.common.ui.toUiText
import mak.app.anikloud.domain.repository.AnimeRepository

internal class DiscoverViewModel(
    private val repository: AnimeRepository
): ViewModel() {

    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    init {
        getAllAnimes()
    }

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

    private fun getAllAnimes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
//            TODO change coroutine dispatchers
            repository.getAiringAnime()
                .onSuccess { animes ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            airingAnime = animes
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
    }

}