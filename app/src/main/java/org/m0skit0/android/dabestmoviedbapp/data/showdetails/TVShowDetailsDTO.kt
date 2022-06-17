package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import com.fasterxml.jackson.annotation.JsonProperty
data class TVShowDetailsDTO(
    @JsonProperty("backdrop_path")
    val backdropPath: String? = "",
    @JsonProperty("created_by")
    val createdBy: List<Any>? = emptyList(), // TODO Fix with actual data
    @JsonProperty("episode_run_time")
    val episodeRunTime: List<Int> = emptyList(),
    @JsonProperty("first_air_date")
    val firstAirDate: String = "",
    @JsonProperty("genres")
    val genres: List<GenreDTO> = emptyList(),
    @JsonProperty("homepage")
    val homepage: String = "",
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("in_production")
    val inProduction: Boolean = false,
    @JsonProperty("languages")
    val languages: List<String> = emptyList(),
    @JsonProperty("last_air_date")
    val lastAirDate: String = "",
    @JsonProperty("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAirDTO = LastEpisodeToAirDTO(),
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("networks")
    val networks: List<NetworkDTO> = emptyList(),
    @JsonProperty("next_episode_to_air")
    val nextEpisodeToAir: Any? = Any(),
    @JsonProperty("number_of_episodes")
    val numberOfEpisodes: Int = 0,
    @JsonProperty("number_of_seasons")
    val numberOfSeasons: Int = 0,
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
    val posterPath: String? = "",
    @JsonProperty("production_companies")
    val productionCompanies: List<ProductionCompanyDTO> = emptyList(),
    @JsonProperty("production_countries")
    val productionCountries: List<ProductionCountryDTO> = emptyList(),
    @JsonProperty("seasons")
    val seasons: List<SeasonDTO>? = emptyList(),
    @JsonProperty("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO> = emptyList(),
    @JsonProperty("status")
    val status: String = "",
    @JsonProperty("tagline")
    val tagline: String = "",
    @JsonProperty("type")
    val type: String = "",
    @JsonProperty("vote_average")
    val voteAverage: Double = 0.0,
    @JsonProperty("vote_count")
    val voteCount: Int = 0,
    @JsonProperty("adult")
    val isAdult: Boolean = false
)

data class GenreDTO(
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = ""
)

data class LastEpisodeToAirDTO(
    @JsonProperty("air_date")
    val airDate: String? = "",
    @JsonProperty("episode_number")
    val episodeNumber: Int = 0,
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("overview")
    val overview: String = "",
    @JsonProperty("production_code")
    val productionCode: String = "",
    @JsonProperty("season_number")
    val seasonNumber: Int = 0,
    @JsonProperty("still_path")
    val stillPath: String? = "",
    @JsonProperty("vote_average")
    val voteAverage: Double = 0.0,
    @JsonProperty("vote_count")
    val voteCount: Int = 0,
    @JsonProperty("runtime")
    val runtime: Int = 0
)

data class NetworkDTO(
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("logo_path")
    val logoPath: String? = "",
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("origin_country")
    val originCountry: String = "",
    @JsonProperty("logo")
    val logo: NetworkLogoDTO? = NetworkLogoDTO()
)

data class NetworkLogoDTO(
    @JsonProperty("path")
    val path: String? = "",
    @JsonProperty("aspect_ratio")
    val aspectRatio: String? = ""
)

data class ProductionCompanyDTO(
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("logo_path")
    val logoPath: String? = "",
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("origin_country")
    val originCountry: String = ""
)

data class ProductionCountryDTO(
    @JsonProperty("iso_3166_1")
    val iso31661: String = "",
    @JsonProperty("name")
    val name: String = ""
)

data class SeasonDTO(
    @JsonProperty("air_date")
    val airDate: String? = "",
    @JsonProperty("episode_count")
    val episodeCount: Int = 0,
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("overview")
    val overview: String = "",
    @JsonProperty("poster_path")
    val posterPath: String? = "",
    @JsonProperty("season_number")
    val seasonNumber: Int = 0,
    @JsonProperty("networks")
    val networks: List<NetworkDTO>? = emptyList()
)

data class SpokenLanguageDTO(
    @JsonProperty("english_name")
    val englishName: String = "",
    @JsonProperty("iso_639_1")
    val iso6391: String = "",
    @JsonProperty("name")
    val name: String = ""
)
