package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.SimilarTVShowApi

data class SimilarTVShowsData(
    val id: Long
)

fun SimilarTVShowApi.toSimilarTVShowsData() = SimilarTVShowsData(id = id.toLong())
