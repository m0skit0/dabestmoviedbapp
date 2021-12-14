package org.m0skit0.android.dabestmoviedbapp.data.toprated

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.numerics.shouldBeZero
import io.kotlintest.matchers.string.shouldBeEmpty
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestTopRatedTVShowsRepositoryImpl {

    @MockK
    private lateinit var mockTopRatedTVShowsService: TopRatedTVShowsService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when top rated tv shows is an empty list, repo should return an empty list`() {
        coEvery { mockTopRatedTVShowsService.topRatedTVShows(any(), any()) } returns TopRatedTVShowsApi()
        runBlocking {
            topRatedTVShowsRepository(
                mockTopRatedTVShowsService,
                { emptyList() },
                1,
            ).fold({ throw it }) { it.shouldBeEmpty() }
        }
    }

    @Test
    fun `when top rated tv shows has empty objects, repo should return an default data class values`() {
        val topRatedTVShows = (1..3).map { TopRatedTVShowApi() }
        coEvery { mockTopRatedTVShowsService.topRatedTVShows(any(), any()) } returns TopRatedTVShowsApi(topRatedTVShows = topRatedTVShows)
        runBlocking {
            topRatedTVShowsRepository(
                mockTopRatedTVShowsService,
                { emptyList() },
                1,
            ).fold({ throw it}) {
                with(it) {
                    size shouldBe 3
                    first().id.shouldBeZero()
                    first().genres.shouldBeEmpty()
                    first().name.shouldBeEmpty()
                }
            }
        }
    }

    @Test
    fun `when top rated tv shows has actual objects, repo should return a correct mapping to data`() {
        val topRatedTVShows = (0..3).map {
            TopRatedTVShowApi(
                id = it.toLong(),
                name = "$it",
                voteAverage = it.toDouble(),
            )
        }
        coEvery { mockTopRatedTVShowsService.topRatedTVShows(any(), any()) } returns TopRatedTVShowsApi(topRatedTVShows = topRatedTVShows)
        runBlocking {
            topRatedTVShowsRepository(
                mockTopRatedTVShowsService,
                { emptyList() },
                1,
            ).fold({ throw it }) {
                with(it) {
                    size shouldBe 4
                    (0..3).forEach {
                        get(it).run {
                            id shouldBe it.toLong()
                            name shouldBe "$it"
                            voteAverage shouldBe it.toDouble()
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `when top rated tv shows has actual objects with valid genres, repo should correctly map genres`() {
        val genresList = listOf("org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1", "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2", "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 3")
        val topRatedTVShows = (0..3).map {
            TopRatedTVShowApi(
                genreIds = listOf(1, 2, 3)
            )
        }
        coEvery { mockTopRatedTVShowsService.topRatedTVShows() } returns TopRatedTVShowsApi(topRatedTVShows = topRatedTVShows)
        runBlocking {
            topRatedTVShowsRepository(
                mockTopRatedTVShowsService,
                { genresList },
                1,
            ).fold({ throw it }) {
                with(it) {
                    (0..3).forEach {
                        get(it).genres shouldBe genresList
                    }
                }
            }
        }
    }
}
