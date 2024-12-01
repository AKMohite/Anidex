package mak.app.anikloud.feature.discover

import mak.app.anikloud.domain.model.Anime

internal sealed interface DiscoverAction {
    data class OnAnimeClick(val anime: Anime): DiscoverAction
}