package mak.app.anikloud.feature.discover

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mak.app.anikloud.core.common.ui.toUiText
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.data.usecase.refresh.RefreshAiringAnimeUseCase
import mak.app.anikloud.data.usecase.update.GetAiringAnimeUseCase
import kotlin.native.concurrent.ThreadLocal

internal class DiscoverViewModel(
    private val refreshAiringAnimeUseCase: RefreshAiringAnimeUseCase,
    private val getAiringAnimeUseCase: GetAiringAnimeUseCase,
    private val dispatcher: Dispatcher
): ViewModel() {


    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * Handle exception to display a message instead of crashing
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }
    /**
     * This is the main scope for all coroutines launched by this ViewModel.
     * Since we pass [viewModelJob], you can cancel all coroutines
     * launched by uiScope by calling [viewModelJob.cancel()]
     */
    private val uiScope: CoroutineScope = CoroutineScope(dispatcher.main + viewModelJob + exceptionHandler)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun handleError(exception: Throwable) {

        val uiText = exception.toUiText()
        _state.update { it.copy(errorMessage = uiText) }
    }


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

//        viewModelScope.launch {
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
//        }
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
        }.invokeOnCompletion {
//            println(it)
//            println(it?.toUiText())
        }
    }

}


