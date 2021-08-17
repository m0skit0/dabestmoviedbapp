package org.m0skit0.android.dabestmoviedbapp.data

import javax.inject.Inject

class TopRatedTVShowsRepositoryMock @Inject constructor() : TopRatedTVShowsRepository {

    private val mockTVShows = listOf(
        TopRatedTVShowData(
            1,
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
        TopRatedTVShowData(
            2,
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

    override suspend fun topRatedTVShows(page: Int): List<TopRatedTVShowData> = mockTVShows
}
