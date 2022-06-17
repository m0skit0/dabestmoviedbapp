package org.m0skit0.android.dabestmoviedbapp.data.toprated
import com.fasterxml.jackson.annotation.JsonProperty


data class TVGenresDTO(
    @JsonProperty("genres")
    val tVGenres: List<TVGenreDTO> = listOf()
)

data class TVGenreDTO(
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = ""
)
