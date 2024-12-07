package mak.app.anikloud.domain.model

internal enum class DiscoverCategory(
    val id: Int,
    val value: String
) {
    BANNER(1, "BANNER"),
    AIRING(2, "Airing"),
    TRENDING(3, "Trending"),
    MOST_POPULAR(4, "Popular"),
    TOP_RATED(5, "Top rated"),
    UPCOMING(6, "Anticipated")
}