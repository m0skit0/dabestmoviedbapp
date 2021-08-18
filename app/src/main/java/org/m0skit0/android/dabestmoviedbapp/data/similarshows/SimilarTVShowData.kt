package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.SimilarTVShowApi

data class SimilarTVShowData(
    val id: Long
)

fun SimilarTVShowApi.toSimilarTVShowsData() = SimilarTVShowData(id = id.toLong())
