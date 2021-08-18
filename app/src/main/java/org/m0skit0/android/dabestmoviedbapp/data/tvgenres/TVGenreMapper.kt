package org.m0skit0.android.dabestmoviedbapp.data.tvgenres

interface TVGenreMapper {
    suspend fun mapGenres(ids: List<Int>) : List<String>
}
