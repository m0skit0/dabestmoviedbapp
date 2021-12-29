package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import java.io.Serializable

data class TopRatedState(
    val currentPage: Int = 1,
    val topRatedShows: List<TopRatedTVShowData> = emptyList(),
    val genreMappingCache: Map<Int, String> = mapOf(),
) : Serializable
