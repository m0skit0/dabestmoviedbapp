package org.m0skit0.android.dabestmoviedbapp.data.tvgenres

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenresApi
import org.m0skit0.android.dabestmoviedbapp.di.koin

private var genreMappingCache: Map<Int, String> = mapOf()

suspend fun mapGenres(
    ids: List<Int>,
    genreMapping: Map<Int, String> = genreMappingCache,
): List<String> = run {
    if (genreMapping.isEmpty()) cacheTVGenres()
    ids.mapFromCache()
}

private fun TVGenresApi.toMap(): Map<Int, String> = tVGenres.associate { it.id to it.name }

private fun List<Int>.mapFromCache(
    genreMapping: Map<Int, String> = genreMappingCache
): List<String> = mapNotNull {
    genreMapping.getOrDefault(it, null)
}

private suspend fun cacheTVGenres(
    tvGenreService: TVGenreService = koin().get(),
) {
    genreMappingCache = tvGenreService.tvGenres().toMap()
}
