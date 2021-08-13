package org.m0skit0.android.dabestmoviedbapp.domain

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.m0skit0.android.dabestmoviedbapp.data.TVShowData
import org.m0skit0.android.dabestmoviedbapp.data.TopRatedTVShowsRepository

class TestTopRatedTVShowsUseCaseImpl {

    @MockK
    private lateinit var mockTopRatedTVShowsRepository: TopRatedTVShowsRepository

    private val topRatedTVShowsUseCaseImpl: TopRatedTVShowsUseCaseImpl
        get() = TopRatedTVShowsUseCaseImpl(mockTopRatedTVShowsRepository)

    private fun tvShowData(id: Int) =
        TVShowData(
            id = id.toLong(),
            imagePath = "$id",
            name = "$id",
            voteAverage = id.toDouble(),
            originalName = "$id",
            overview = "$id",
            genres = listOf("$id"),
            firstAirDate = "$id",
            originCountry = listOf("$id"),
            originalLanguage = "$id",
            popularity = id.toDouble(),
            voteCount = id,
        )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when calling use case, it should call the repository`() {
        every { mockTopRatedTVShowsRepository.topRatedTVShows() } returns flow { emit(emptyList<TVShowData>()) }
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows()
        }
        verify {
            mockTopRatedTVShowsRepository.topRatedTVShows()
        }
    }

    @Test
    fun `when calling use case once, it should call the repository just once`() {
        every { mockTopRatedTVShowsRepository.topRatedTVShows() } returns flow { emit(emptyList<TVShowData>()) }
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows()
        }
        verify(exactly = 1) {
            mockTopRatedTVShowsRepository.topRatedTVShows()
        }
    }

    @Test
    fun `when calling use case, if the repo returns an empty list it should map to an empty list`() {
        every { mockTopRatedTVShowsRepository.topRatedTVShows() } returns flow { emit(emptyList<TVShowData>()) }
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows().collect {
                it.shouldBeEmpty()
            }
        }
    }

    @Test
    fun `when calling use case, if the repo returns an filled list it should map it correctly`() {
        val tvShowDataList = (1..3).map { tvShowData(it) }
        every { mockTopRatedTVShowsRepository.topRatedTVShows() } returns flow {
            emit(tvShowDataList)
        }
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows().collect { list ->
                list shouldBe (1..3).map {
                    TVShowDomain(
                        id = it.toLong(),
                        imagePath = "$it",
                        name = "$it",
                        voteAverage = it.toDouble(),
                        originalName = "$it",
                        overview = "$it",
                        genres = listOf("$it"),
                        firstAirDate = "$it",
                        originCountry = listOf("$it"),
                        originalLanguage = "$it",
                        popularity = it.toDouble(),
                        voteCount = it,
                    )
                }
            }
        }
    }
}
