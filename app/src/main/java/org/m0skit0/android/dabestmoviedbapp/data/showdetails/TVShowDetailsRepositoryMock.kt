package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import javax.inject.Inject

class TVShowDetailsRepositoryMock @Inject constructor(): TVShowDetailsRepository {

    private val detailsMap = mapOf(
        1L to TVShowDetailsData(
            id = 1,
            posterPath = "somepath",
            name = "The Expanse",
            overview = "Best series ever",
            voteAverage = "10.0",
            voteCount = "1",
            genres = listOf("Science-Fiction", "Space", "Aliens"),
        ),
        2L to TVShowDetailsData(
            id = 2,
            posterPath = "anotherpath",
            name = "Breaking Bad",
            overview = "5th season was not necessary",
            voteAverage = "8.5",
            voteCount = "1",
            genres = listOf("Family", "Drugs", "Chemistry"),
        ),
    )

    override suspend fun tvShowDetails(id: Long): TVShowDetailsData = detailsMap.getOrElse(id) {
        TVShowDetailsData(
            id = 0,
            posterPath = "",
            name = "",
            overview = "",
            voteAverage = "",
            voteCount = "",
            genres = emptyList(),
        )
    }
}
