package org.m0skit0.android.dabestmoviedbapp.data.retrofit
import com.fasterxml.jackson.annotation.JsonProperty


data class TVGenres(
    @JsonProperty("genres")
    val tVGenres: List<TVGenre> = listOf()
)

data class TVGenre(
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = ""
)
