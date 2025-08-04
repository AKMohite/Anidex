package mak.app.anikloud.core.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class AnimeDTO(
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("synopsis")
    val synopsis: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("coverImageTopOffset")
    val coverImageTopOffset: Int? = null,
    @SerialName("titles")
    val titles: TitleDTO? = null,
    @SerialName("canonicalTitle")
    val canonicalTitle: String? = null,
    @SerialName("abbreviatedTitles")
    val abbreviatedTitles: List<String>? = null,
    @SerialName("averageRating")
    val averageRating: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("nextRelease")
    val nextRelease: String? = null,
    @SerialName("popularityRank")
    val popularityRank: Int? = null,
    @SerialName("ratingRank")
    val ratingRank: Int? = null,
    @SerialName("ageRating")
    val ageRatingCertificate: String? = null,
    @SerialName("ageRatingGuide")
    val ageRatingGuide: String? = null,
    @SerialName("subtype")
    val subtype: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("tba")
    val tba: String? = null,
    @SerialName("posterImage")
    val posterImage: ImageDTO? = null,
    @SerialName("coverImage")
    val coverImage: ImageDTO? = null,
    @SerialName("episodeCount")
    val episodeCount: Int? = null,
    @SerialName("episodeLength")
    val episodeLength: Int? = null,
    @SerialName("totalLength")
    val totalLength: Int? = null,
    @SerialName("youtubeVideoId")
    val youtubeVideoId: String? = null,
    @SerialName("showType")
    val showType: String? = null,
    @SerialName("nsfw")
    val nsfw: Boolean? = null

)
