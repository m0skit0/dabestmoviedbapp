package org.m0skit0.android.dabestmoviedbapp.data.similarshows

data class SimilarTVShowData(
    val id: Long
)

fun SimilarTVShowDTO.toSimilarTVShowsData() = SimilarTVShowData(id = id.toLong())
