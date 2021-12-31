package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowData
import java.io.Serializable

data class SimilarShowsState(
    val currentShowId: Long = -1L,
    val similarShows: List<SimilarTVShowData> = emptyList()
) : ApplicationState(), Serializable

infix fun SimilarShowsState.updateSimilarShowsWith(newList: List<SimilarTVShowData>): SimilarShowsState =
    copy(similarShows = newList)
