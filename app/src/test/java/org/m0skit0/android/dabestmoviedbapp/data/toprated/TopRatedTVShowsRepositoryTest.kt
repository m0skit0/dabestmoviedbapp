package org.m0skit0.android.dabestmoviedbapp.data.toprated

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.result.shouldBeFailureOfType
import io.kotest.matchers.result.shouldBeSuccess
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
        When("tvGenreService throws an exception") {
            coEvery { mockTopRatedTVShowsService.topRatedTVShows(any(), any()) } returns TopRatedTVShowsDTO()
            coEvery { mockTVGenreService.tvGenres(any()) } throws IOException()
            Then("It should return a failure if genres are not cached") {
                topRatedTVShowsRepository(
                    state = TopRatedState(),
                    topRatedTVShowsService = mockTopRatedTVShowsService,
                    tvGenreService = mockTVGenreService
                ).shouldBeFailureOfType<IOException>()
            }
            Then("It should return a success if genres are cached") {
                topRatedTVShowsRepository(
                    state = TopRatedState(genreMappingCache = mapOf(1 to "foo")),
                    topRatedTVShowsService = mockTopRatedTVShowsService,
                    tvGenreService = mockTVGenreService
                ).shouldBeSuccess()
            }
        }
    }
})
