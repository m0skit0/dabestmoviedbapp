package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData

data class TopRatedState(
    val currentPage: Int = 1,
    val topRatedShows: List<TopRatedTVShowData> = emptyList(),
    val genreMappingCache: Map<Int, String> = mapOf(),
)

infix fun TopRatedState.updateGenreMappingCacheWith(newMap: Map<Int, String>): TopRatedState =
    copy(genreMappingCache = newMap)

infix fun TopRatedState.updateCurrentPageWith(newPage: Int): TopRatedState =
    copy(currentPage = newPage)

fun TopRatedState.updateWithNextPage(): TopRatedState = updateCurrentPageWith(currentPage.inc())

infix fun TopRatedState.updateTopRatedShowsWith(newList: List<TopRatedTVShowData>): TopRatedState =
    copy(topRatedShows = topRatedShows + newList)
