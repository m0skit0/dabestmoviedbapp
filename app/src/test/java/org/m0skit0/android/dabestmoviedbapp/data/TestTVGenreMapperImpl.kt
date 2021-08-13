package org.m0skit0.android.dabestmoviedbapp.data

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenresApi

class TestTVGenreMapperImpl {

    @MockK
    private lateinit var mockTvGenreService: TVGenreService

    private val tvGenreMapperImpl: TVGenreMapperImpl
        get() = TVGenreMapperImpl(mockTvGenreService)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when mapping an empty list of genres against an empty genre API, mapping should also return an empty list`() {
        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi()
        runBlocking {
            tvGenreMapperImpl.mapGenres(emptyList()).shouldBeEmpty()
        }
    }

    @Test
    fun `when mapping a non-empty list of genres against an empty genre API, mapping should also return an empty list`() {
        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi()
        runBlocking {
            tvGenreMapperImpl.mapGenres(listOf(1, 2, 3)).shouldBeEmpty()
        }
    }

    @Test
    fun `when mapping a empty list of genres against a non-empty genre API, mapping should also return an empty list`() {
        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi(
            tVGenres = listOf(TVGenreApi(name = "Genre 1"), TVGenreApi(name = "Genre 2"))
        )
        runBlocking {
            tvGenreMapperImpl.mapGenres(emptyList()).shouldBeEmpty()
        }
    }

    @Test
    fun `when mapping a valid non-empty list of genres against a non-empty genre API, mapping should be done correctly`() {
        coEvery { mockTvGenreService.tvGenres() } returns
                TVGenresApi(
                    tVGenres = listOf(
                        TVGenreApi(id = 1, name = "Genre 1"),
                        TVGenreApi(id = 2, name = "Genre 2"),
                        TVGenreApi(id = 3, name = "Genre 3"),
                    )
                )
        runBlocking {
            tvGenreMapperImpl.mapGenres(listOf(1, 2)) shouldBe listOf("Genre 1", "Genre 2")
        }
    }

    @Test
    fun `when mapping an invalid list of genres against a genre API, mapping should ignore invalid genre ids`() {
        coEvery { mockTvGenreService.tvGenres() } returns
                TVGenresApi(
                    tVGenres = listOf(
                        TVGenreApi(id = 1, name = "Genre 1"),
                        TVGenreApi(id = 2, name = "Genre 2")
                    )
                )
        runBlocking {
            tvGenreMapperImpl.mapGenres(listOf(2, 4)) shouldBe listOf("Genre 2")
        }
    }

    @Test
    fun `when mapping a list of genres against a genre API with no intersection, mapping should return an empty list`() {
        coEvery { mockTvGenreService.tvGenres() } returns
                TVGenresApi(
                    tVGenres = listOf(
                        TVGenreApi(id = 1, name = "Genre 1"),
                        TVGenreApi(id = 2, name = "Genre 2")
                    )
                )
        runBlocking {
            tvGenreMapperImpl.mapGenres(listOf(3, 4)) shouldBe emptyList()
        }
    }
}
