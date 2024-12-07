package mak.app.anikloud.feature.discover

import mak.app.anikloud.core.common.ui.UiText
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.model.DiscoverCategory


internal data class DiscoverSection(
    val type: DiscoverCategory,
    val animes: List<Anime>,
    val isLoading: Boolean = false
)

internal data class DiscoverState(
    val sections: List<DiscoverSection> = emptyList(),
    val isLoading: Boolean = false,
    val selectedAnime: Long? = null,
    val errorMessage: UiText? = null
) {
    fun isNotEmpty(): Boolean {
        return sections.any {
            it.animes.isNotEmpty()
        }
    }

    val categorisedAnime: List<DiscoverSection>
        get() {
            return sections.filter { section ->
                section.animes.isNotEmpty()
            }.sortedBy { section ->
                section.type.id // todo: maybe we don't need this and we can just have type to have top
            }
        }
}