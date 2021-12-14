package org.m0skit0.android.dabestmoviedbapp.data.tvgenres
import com.fasterxml.jackson.annotation.JsonProperty


data class TVGenresApi(
    @JsonProperty("genres")
    val tVGenres: List<TVGenreApi> = listOf()
)

data class TVGenreApi(
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = ""
)
