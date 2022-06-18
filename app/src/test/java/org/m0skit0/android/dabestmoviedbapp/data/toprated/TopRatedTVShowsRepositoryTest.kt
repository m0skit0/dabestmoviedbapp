package org.m0skit0.android.dabestmoviedbapp.data.toprated

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.result.shouldBeFailureOfType
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
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
        When("Both services are success") {
            And("Both services return empty lists") {
                coEvery {
                    mockTopRatedTVShowsService.topRatedTVShows(
                        any(),
                        any()
                    )
                } returns TopRatedTVShowsDTO()
                coEvery { mockTVGenreService.tvGenres(any()) } returns TVGenresDTO()
                Then("It should return a success with empty lists") {
                    topRatedTVShowsRepository(
                        state = TopRatedState(),
                        topRatedTVShowsService = mockTopRatedTVShowsService,
                        tvGenreService = mockTVGenreService
                    ).shouldBeSuccess {
                        it.topRatedShows.shouldBeEmpty()
                        it.genreMappingCache.shouldBeEmpty()
                    }
                }
            }
            And("TV genres is not a empty list") {
                val tvGenres = listOf(
                    TVGenreDTO(id = 1, name = "foo"),
                    TVGenreDTO(id = 2, name = "bar"),
                )
                val tvGenresMap = tvGenres.associate { it.id to it.name }
                coEvery { mockTopRatedTVShowsService.topRatedTVShows(any(), any()) } returns TopRatedTVShowsDTO()
                coEvery { mockTVGenreService.tvGenres(any()) } returns TVGenresDTO(tVGenres = tvGenres)
                Then("It should call tvGenreService and fill state with it if cache is empty") {
                    topRatedTVShowsRepository(
                        state = TopRatedState(),
                        topRatedTVShowsService = mockTopRatedTVShowsService,
                        tvGenreService = mockTVGenreService
                    ).shouldBeSuccess {
                        it.genreMappingCache shouldBe tvGenresMap
                    }
                    coVerify(exactly = 1) {
                        mockTVGenreService.tvGenres(any())
                    }
                }
                Then("It should not call tvGenreService if cache is not empty") {
                    topRatedTVShowsRepository(
                        state = TopRatedState(
                            genreMappingCache = tvGenresMap
                        ),
                        topRatedTVShowsService = mockTopRatedTVShowsService,
                        tvGenreService = mockTVGenreService
                    ).shouldBeSuccess {
                        it.genreMappingCache shouldBe tvGenresMap
                    }
                    coVerify(exactly = 1) {
                        mockTVGenreService.tvGenres(any()) wasNot Called
                    }
                }
            }
        }
    }
})
