package org.m0skit0.android.dabestmoviedbapp.data

interface TVGenreMapper {
    suspend fun mapGenres(ids: List<Int>) : List<String>
}
