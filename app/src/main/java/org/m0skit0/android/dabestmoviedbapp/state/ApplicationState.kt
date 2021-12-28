package org.m0skit0.android.dabestmoviedbapp.state

import java.io.Serializable

data class ApplicationState(
    val topRatedState: TopRatedState = TopRatedState(),
    val showDetailsState: ShowDetailsState = ShowDetailsState()
) : Serializable

data class TopRatedState(
    val currentPage: Int = 1,
    val genreMappingCache: Map<Int, String> = mapOf(),
) : Serializable

data class ShowDetailsState(
    val currentShowId: Long = -1L,
) : Serializable

infix fun ApplicationState.updateGenreMappingCacheWith(newMap: Map<Int, String>): ApplicationState =
    copy(topRatedState = topRatedState.copy(genreMappingCache = newMap))

infix fun ApplicationState.updateCurrentPageWith(newPage: Int): ApplicationState =
    copy(topRatedState = topRatedState.copy(currentPage = newPage))

fun ApplicationState.updateWithNextPage(): ApplicationState = updateCurrentPageWith(topRatedState.currentPage + 1)

infix fun ApplicationState.updateCurrentShowIdWith(newShowId: Long): ApplicationState =
    copy(showDetailsState = showDetailsState.copy(currentShowId = newShowId))
