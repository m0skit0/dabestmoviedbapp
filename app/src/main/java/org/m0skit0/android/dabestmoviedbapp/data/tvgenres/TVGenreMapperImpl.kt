package org.m0skit0.android.dabestmoviedbapp.data.tvgenres

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenresApi
import javax.inject.Inject

class TVGenreMapperImpl
@Inject constructor(
    private val tvGenreService: TVGenreService
) : TVGenreMapper {

    private var genreMapping: Map<Int, String> = mapOf()

    override suspend fun mapGenres(ids: List<Int>): List<String> = run {
        if (genreMapping.isEmpty()) {
            genreMapping = tvGenreService.tvGenres().toMap()
        }
        ids.mapFromCache()
    }

    private fun TVGenresApi.toMap(): Map<Int, String> = tVGenres.associate { it.id to it.name }

    private fun List<Int>.mapFromCache(): List<String> = mapNotNull {
        genreMapping.getOrDefault(it, null)
    }
}
