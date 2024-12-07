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
    private val refreshPopularAnimeUseCase: RefreshAnimeUsecase,
    private val getBannerUseCase: GetAnimeUsecase,
    private val getAiringAnimeUseCase: GetAnimeUsecase,
    private val getPopularAnimeUseCase: GetAnimeUsecase,
    private val getTrendingAnimeUseCase: GetAnimeUsecase,
    dispatcher: Dispatcher
): BaseViewModel(
    dispatcher = dispatcher
) {
    private var isFetchingAnime = false
    private var isRefreshingAnime = false
    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    init {
        initiateSection()
        initiateDiscover()
    }

    private fun initiateSection() {
        uiScope.launch {
            val sections = DiscoverCategory.entries.map {
                DiscoverSection(type = it)
            }
            _state.update { it.copy(sections = sections) }
        }
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
            DiscoverCategory.MOST_POPULAR -> getPopularAnimeUseCase(1)
            else -> getAiringAnimeUseCase(1)
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
                            animes = animes
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
    }

    private fun refreshAnimes() {
        if (isRefreshingAnime) return

        uiScope.launch {
            isRefreshingAnime = true
            _state.update { currentState -> currentState.copy(
                sections = currentState.sections.map { section -> section.copy(isLoading = true) }
            ) }
            refreshAiringAnimeUseCase(1)
            refreshTrendingAnimeUseCase(1)
            refreshPopularAnimeUseCase(1)
        }.invokeOnCompletion {
            isRefreshingAnime = false
            _state.update { currentState -> currentState.copy(
                sections = currentState.sections.map { section -> section.copy(isLoading = false) }
            ) }
        }
    }

}


