package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowData
import java.io.Serializable

data class SimilarShowsState(
    val similarShows: List<SimilarTVShowData> = emptyList()
) : Serializable
