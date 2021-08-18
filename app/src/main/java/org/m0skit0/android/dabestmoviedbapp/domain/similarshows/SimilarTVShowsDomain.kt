package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowData

data class SimilarTVShowsDomain(
    val id: Long
)

fun SimilarTVShowData.toSimilarTVShowsDomain() = SimilarTVShowsDomain(id = id)
