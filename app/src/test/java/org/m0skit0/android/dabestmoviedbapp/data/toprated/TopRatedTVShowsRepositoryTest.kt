package org.m0skit0.android.dabestmoviedbapp.data.toprated

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.result.shouldBeFailureOfType
import io.mockk.coEvery
import io.mockk.mockk
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState
import java.io.IOException

class TopRatedTVShowsRepositoryTest : BehaviorSpec({

    lateinit var mockTopRatedTVShowsService: TopRatedTVShowsService
    lateinit var mockTVGenreService: TVGenreService

    beforeContainer {
        mockTopRatedTVShowsService = mockk()
        mockTVGenreService = mockk()
    }

    Given("A TopRatedTVShowsRepository") {
        When("topRatedTVShowsService throws an exception") {
            coEvery { mockTopRatedTVShowsService.topRatedTVShows(any(), any()) } throws IOException()
            Then("It should return a failure") {
                topRatedTVShowsRepository(
                    state = TopRatedState(),
                    topRatedTVShowsService = mockTopRatedTVShowsService,
                    tvGenreService = mockTVGenreService
                ).shouldBeFailureOfType<IOException>()
            }
        }
    }
})
