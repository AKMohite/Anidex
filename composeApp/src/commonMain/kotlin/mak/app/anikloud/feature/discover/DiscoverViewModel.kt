package mak.app.anikloud.feature.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mak.app.anikloud.data.usecase.refresh.RefreshAiringAnimeUseCase
import mak.app.anikloud.data.usecase.update.GetAiringAnimeUseCase

internal class DiscoverViewModel(
    private val refreshAiringAnimeUseCase: RefreshAiringAnimeUseCase,
    private val getAiringAnimeUseCase: GetAiringAnimeUseCase
): ViewModel() {

    private var isFetchingAnime = false
    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    init {
        initiateDiscover()
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

    private fun initiateDiscover() {

        refreshAnimes()
        getAnimes()

        viewModelScope.launch {
//            _state.update { it.copy(isLoading = true) }
////            TODO change coroutine dispatchers
//            repository.getAiringAnime()
//                .onSuccess { animes ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                            airingAnime = animes
//                        )
//                    }
//                }
//                .onError { error ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                            errorMessage = error.toUiText()
//                        )
//                    }
//                }
        }
    }

    private fun getAnimes() {
        getAiringAnimeUseCase(1)
            .onStart { isFetchingAnime = true }
            .onEach { animes ->
                _state.update {
                    it.copy(airingAnime = animes)
                }
            }
            .onCompletion { isFetchingAnime = true }
            .catch {
                println(it)
            }.launchIn(viewModelScope)
    }

    private fun refreshAnimes() {
        viewModelScope.launch {
            refreshAiringAnimeUseCase(1)
        }.invokeOnCompletion {
            println(it)
        }
    }

}


