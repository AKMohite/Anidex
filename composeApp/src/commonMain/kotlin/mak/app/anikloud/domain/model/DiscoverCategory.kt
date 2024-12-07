package mak.app.anikloud.domain.model

internal enum class DiscoverCategory(
    val id: Int,
    val value: String
) {
    AIRING(1, "Airing"),
    TRENDING(2, "Trending"),
    MOST_POPULAR(3, "Popular"),
    HIGHEST_RATED(4, "Top rated"),
    UPCOMING(5, "Anticipated")

}