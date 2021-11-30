package org.m0skit0.android.dabestmoviedbapp.data.tvgenres

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenresApi
import org.m0skit0.android.dabestmoviedbapp.di.koin

private var genreMappingCache: Map<Int, String> = mapOf()

suspend fun mapGenres(
    ids: List<Int>,
    tvGenreService: TVGenreService = koin().get(),
    genreMapping: Map<Int, String> = genreMappingCache,
): List<String> = run {
    if (genreMapping.isEmpty()) {
        genreMappingCache = tvGenreService.tvGenres().toMap()
    }
    ids.mapFromCache()
}

private fun TVGenresApi.toMap(): Map<Int, String> = tVGenres.associate { it.id to it.name }

private fun List<Int>.mapFromCache(
    genreMapping: Map<Int, String> = genreMappingCache
): List<String> = mapNotNull {
    genreMapping.getOrDefault(it, null)
}
