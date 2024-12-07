package mak.app.anikloud.feature.discover

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mak.app.anikloud.core.common.ui.base.BaseViewModel
import mak.app.anikloud.core.common.ui.toUiText
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.model.DiscoverCategory
import mak.app.anikloud.domain.usecase.GetAnimeUsecase
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase

internal class DiscoverViewModel(
    private val refreshAiringAnimeUseCase: RefreshAnimeUsecase,
    private val refreshTrendingAnimeUseCase: RefreshAnimeUsecase,
    private val getBannerUseCase: GetAnimeUsecase,
    private val getAiringAnimeUseCase: GetAnimeUsecase,
    private val getTrendingAnimeUseCase: GetAnimeUsecase,
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

    fun observeAnime(discoverCategory: DiscoverCategory): Flow<Pair<DiscoverCategory, List<Anime>>> {
        val flowAnime = when(discoverCategory) {
            DiscoverCategory.BANNER -> getBannerUseCase(1)
            DiscoverCategory.AIRING -> getAiringAnimeUseCase(1)
            DiscoverCategory.TRENDING -> getTrendingAnimeUseCase(1)
            else -> getAiringAnimeUseCase(1)
//            DiscoverCategory.MOST_POPULAR -> TODO()
//            DiscoverCategory.HIGHEST_RATED -> TODO()
//            DiscoverCategory.UPCOMING -> TODO()
        }
        return flowAnime
            .map { Pair(discoverCategory, it) }
    }

    private fun getAnimes() {
        combine(
            flows = DiscoverCategory.entries.map(::observeAnime),
            transform = { allSectionAnime -> allSectionAnime.toList() }
        ).onStart { isFetchingAnime = true }
            .map { allSectionAnime ->
                val sections = allSectionAnime
                    .map { (category, animes) ->
                        DiscoverSection(
                            type = category,
                            animes = animes,
                            //                            isLoading =
                        )
                    }
                sections
            }.onEach { sections ->
                _state.update {
                    it.copy(sections = sections)
                }
            }
            .onCompletion { isFetchingAnime = true }
            .launchIn(uiScope)

//        getBannerUseCase(1)
//            .onStart { isFetchingAnime = true }
//            .onEach { animes ->
//                _state.update {
//                    it.copy(airingAnime = animes)
//                }
//            }
//            .onCompletion { isFetchingAnime = true }
//            .catch { throwable ->
//                _state.update {
//                    it.copy(
//                        isLoading = false,
//                        errorMessage = throwable.toUiText()
//                    )
//                }
//            }.launchIn(uiScope)
    }

    private fun refreshAnimes() {
        uiScope.launch {
            refreshAiringAnimeUseCase(1)
            refreshTrendingAnimeUseCase(1)
        }
    }

}


