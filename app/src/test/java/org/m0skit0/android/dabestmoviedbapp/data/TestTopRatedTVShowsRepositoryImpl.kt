package org.m0skit0.android.dabestmoviedbapp.data

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.numerics.shouldBeZero
import io.kotlintest.matchers.string.shouldBeEmpty
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService

class TestTopRatedTVShowsRepositoryImpl {

    @MockK
    private lateinit var mockTopRatedTVShowsService: TopRatedTVShowsService

    @MockK
    private lateinit var mockTVGenreMapper: TVGenreMapper

    private val topRatedTVShowsRepositoryImpl: TopRatedTVShowsRepositoryImpl
        get() = TopRatedTVShowsRepositoryImpl(
            mockTopRatedTVShowsService,
            mockTVGenreMapper
        )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when top rated tv shows is an empty list, repo should return an empty list`() {
        every { mockTopRatedTVShowsService.topRatedTVShows() } returns flow {
            emit(TopRatedTVShowsApi())
        }
        runBlocking {
            topRatedTVShowsRepositoryImpl.topRatedTVShows().collect {
                it shouldBe emptyList()
            }
        }
    }

    @Test
    fun `when top rated tv shows has empty objects, repo should return an default data class values`() {
        val topRatedTVShows = (1..3).map { TopRatedTVShowApi() }
        every { mockTopRatedTVShowsService.topRatedTVShows() } returns flow {
            emit(TopRatedTVShowsApi(topRatedTVShows = topRatedTVShows))
        }
        every { mockTVGenreMapper.mapGenres(any()) } returns flow { emit(emptyList<String>()) }
        runBlocking {
            topRatedTVShowsRepositoryImpl.topRatedTVShows().collect {
                it.size shouldBe 3
                it.first().id.shouldBeZero()
                it.first().imagePath.shouldBeEmpty()
                it.first().genres.shouldBeEmpty()
                it.first().name.shouldBeEmpty()
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
        every { mockTopRatedTVShowsService.topRatedTVShows() } returns flow {
            emit(TopRatedTVShowsApi(topRatedTVShows = topRatedTVShows))
        }
        every { mockTVGenreMapper.mapGenres(any()) } returns flow { emit(emptyList<String>()) }
        runBlocking {
            topRatedTVShowsRepositoryImpl.topRatedTVShows().collect { list ->
                list.size shouldBe 4
                (0..3).forEach {
                    list[it].run {
                        id shouldBe it.toLong()
                        name shouldBe "$it"
                        voteAverage shouldBe it.toDouble()
                    }
                }
            }
        }
    }

    @Test
    fun `when top rated tv shows has actual objects with valid genres, repo should correctly map genres`() {
        val genresList = listOf("Genre 1", "Genre 2", "Genre 3")
        val topRatedTVShows = (0..3).map {
            TopRatedTVShowApi(
                genreIds = listOf(1, 2, 3)
            )
        }
        every { mockTopRatedTVShowsService.topRatedTVShows() } returns flow {
            emit(TopRatedTVShowsApi(topRatedTVShows = topRatedTVShows))
        }
        every { mockTVGenreMapper.mapGenres(any()) } returns flow { emit(genresList) }
        runBlocking {
            topRatedTVShowsRepositoryImpl.topRatedTVShows().collect { list ->
                (0..3).forEach {
                    list[it].genres shouldBe genresList
                }
            }
        }
    }
}
