package org.m0skit0.android.dabestmoviedbapp.data.tvgenres

import org.m0skit0.android.dabestmoviedbapp.di.koin

typealias TVGenresRepository = suspend (ids: List<Int>) -> List<String>

private var genreMappingCache: Map<Int, String> = mapOf()

suspend fun cacheAndMapTVGenres(ids: List<Int>): List<String> = run {
    cacheTVGenres()
    mapTVGenres(ids)
}

fun mapTVGenres(
    ids: List<Int>,
    genreMapping: Map<Int, String> = genreMappingCache
): List<String> = ids.mapNotNull { genreMapping.getOrDefault(it, null) }

suspend fun cacheTVGenres(
    tvGenreService: TVGenreService = koin().get(),
    genreMapping: Map<Int, String> = genreMappingCache,
) {
    if (genreMapping.isEmpty()) {
        genreMappingCache = tvGenreService.tvGenres().toMap()
    }
}

private fun TVGenresApi.toMap(): Map<Int, String> = tVGenres.associate { it.id to it.name }
