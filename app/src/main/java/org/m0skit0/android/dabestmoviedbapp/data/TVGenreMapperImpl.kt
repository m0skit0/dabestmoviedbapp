package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import javax.inject.Inject

class TVGenreMapperImpl
@Inject constructor(
    private val tvGenreService: TVGenreService
) : TVGenreMapper {

    // TODO Cache the mapped values instead of calling the API every time
    override fun mapGenres(ids: List<Int>): Flow<List<String>> =
        tvGenreService.tvGenres().map { tvGenres ->
            ids.map { id -> tvGenres.tVGenres.first { it.id == id }.name }
        }
}
