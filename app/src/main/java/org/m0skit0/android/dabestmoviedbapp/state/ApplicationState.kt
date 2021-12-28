package org.m0skit0.android.dabestmoviedbapp.state

import arrow.optics.optics
import java.io.Serializable

@optics
data class ApplicationState(
    val topRatedState: TopRatedState,
    val showDetailsState: ShowDetailsState
) : Serializable { companion object }

@optics
data class TopRatedState(
    val currentPage: Int = 1,
    val genreMappingCache: Map<Int, String> = mapOf(),
) : Serializable { companion object }

@optics
data class ShowDetailsState(
    val currentShowId: Long = -1L,
) : Serializable { companion object }
