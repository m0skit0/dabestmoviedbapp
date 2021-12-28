package org.m0skit0.android.dabestmoviedbapp.data.tvgenres

import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias TVGenresRepository = suspend (state: ApplicationState, ids: List<Int>) -> List<String>

fun mapTVGenres(
    state: ApplicationState,
    ids: List<Int>,
): List<String> = ids.mapNotNull { state.genreMappingCache.getOrDefault(it, null) }

suspend fun cacheTVGenres(
    state: ApplicationState,
    tvGenreService: () -> TVGenreService = { koin().get() },
): ApplicationState =
    if (state.genreMappingCache.isEmpty())
        tvGenreService().tvGenres().toMap().let {
            state.copy(genreMappingCache = it)
        }
    else state

private fun TVGenresApi.toMap(): Map<Int, String> = tVGenres.associate { it.id to it.name }
