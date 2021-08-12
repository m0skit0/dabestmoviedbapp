package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import com.fasterxml.jackson.annotation.JsonProperty

data class TopRatedTVShowsApi(
    @JsonProperty("page")
    val page: Int = 0,
    @JsonProperty("results")
    val topRatedTVShows: List<TopRatedTVShowApi> = emptyList(),
    @JsonProperty("total_pages")
    val totalPages: Int = 0,
    @JsonProperty("total_results")
    val totalResults: Int = 0
)

data class TopRatedTVShowApi(
    @JsonProperty("backdrop_path")
    val backdropPath: String = "",
    @JsonProperty("first_air_date")
    val firstAirDate: String = "",
    @JsonProperty("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("origin_country")
    val originCountry: List<String> = emptyList(),
    @JsonProperty("original_language")
    val originalLanguage: String = "",
    @JsonProperty("original_name")
    val originalName: String = "",
    @JsonProperty("overview")
    val overview: String = "",
    @JsonProperty("popularity")
    val popularity: Double = 0.0,
    @JsonProperty("poster_path")
    val posterPath: String = "",
    @JsonProperty("vote_average")
    val voteAverage: Double = 0.0,
    @JsonProperty("vote_count")
    val voteCount: Int = 0
)
