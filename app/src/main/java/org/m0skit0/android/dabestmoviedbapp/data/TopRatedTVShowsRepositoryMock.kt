package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopRatedTVShowsRepositoryMock : TopRatedTVShowsRepository {

    private val mockTVShows = listOf(
        TVShowData(
            "somepath",
            "The Expanse",
            10.0,
            "The Expanse",
            "Best series ever",
            listOf("Science-Fiction"),
            "14/12/2015",
            listOf("us"),
            "en",
            10.0,
            1,
        ),
        TVShowData(
            "anotherpath",
            "Breaking Bad",
            8.5,
            "Breaking Bad",
            "5th season was not necessary",
            listOf("Family"),
            "20/01/2008",
            listOf("us"),
            "en",
            10.0,
            1,
        ),
    )

    override fun topRatedTVShows(): Flow<List<TVShowData>> = flow { emit(mockTVShows) }
}
