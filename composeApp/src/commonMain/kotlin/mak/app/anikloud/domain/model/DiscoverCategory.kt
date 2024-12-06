package mak.app.anikloud.domain.model

internal enum class DiscoverCategory(
    val id: Int,
    val value: String
) {
    AIRING(1, "Airing"),
    UPCOMING(2, "Anticipated"),
    TRENDING(3, "Trending"),
    HIGHEST_RATED(4, "Top rated"),
    MOST_POPULAR(5, "Popular")

}