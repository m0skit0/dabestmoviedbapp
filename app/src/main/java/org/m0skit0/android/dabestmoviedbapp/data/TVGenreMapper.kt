package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.flow.Flow

interface TVGenreMapper {
    fun mapGenres(ids: List<Int>) : Flow<List<String>>
}
