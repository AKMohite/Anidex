package mak.app.anikloud.feature.discover

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mak.app.anikloud.core.common.ui.base.BaseViewModel
import mak.app.anikloud.core.common.ui.toUiText
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.data.usecase.refresh.RefreshAiringAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetAiringAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetBannerUseCase

internal class DiscoverViewModel(
    private val refreshAiringAnimeUseCase: RefreshAiringAnimeUseCase,
    private val getAiringAnimeUseCase: GetAiringAnimeUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    dispatcher: Dispatcher
): BaseViewModel(
    dispatcher = dispatcher
) {
    private var isFetchingAnime = false
    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    init {
        initiateDiscover()
    }

    override fun handleError(exception: Throwable) {
        val uiText = exception.toUiText()
        _state.update { it.copy(errorMessage = uiText) }
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
    }

    private fun getAnimes() {
        getBannerUseCase(1)
            .onStart { isFetchingAnime = true }
            .onEach { animes ->
                _state.update {
                    it.copy(airingAnime = animes)
                }
            }
            .onCompletion { isFetchingAnime = true }
            .catch { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.toUiText()
                    )
                }
            }.launchIn(uiScope)
    }

    private fun refreshAnimes() {
        uiScope.launch {
            refreshAiringAnimeUseCase(1)
        }
    }

}


