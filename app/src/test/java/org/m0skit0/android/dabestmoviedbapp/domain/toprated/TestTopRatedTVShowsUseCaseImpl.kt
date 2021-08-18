package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository

class TestTopRatedTVShowsUseCaseImpl {

    @MockK
    private lateinit var mockTopRatedTVShowsRepository: TopRatedTVShowsRepository

    private val topRatedTVShowsUseCaseImpl: TopRatedTVShowsUseCaseImpl
        get() = TopRatedTVShowsUseCaseImpl(mockTopRatedTVShowsRepository)

    private fun tvShowData(id: Int) =
        TopRatedTVShowData(
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
        coEvery { mockTopRatedTVShowsRepository.topRatedTVShows() } returns emptyList()
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows()
        }
        coVerify {
            mockTopRatedTVShowsRepository.topRatedTVShows()
        }
    }

    @Test
    fun `when calling use case once, it should call the repository just once`() {
        coEvery { mockTopRatedTVShowsRepository.topRatedTVShows() } returns emptyList()
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows()
        }
        coVerify(exactly = 1) {
            mockTopRatedTVShowsRepository.topRatedTVShows()
        }
    }

    @Test
    fun `when calling use case, if the repo returns an empty list it should map to an empty list`() {
        coEvery { mockTopRatedTVShowsRepository.topRatedTVShows() } returns emptyList<TopRatedTVShowData>()
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows().shouldBeEmpty()
        }
    }

    @Test
    fun `when calling use case, if the repo returns an filled list it should map it correctly`() {
        val tvShowDataList = (1..3).map { tvShowData(it) }
        coEvery { mockTopRatedTVShowsRepository.topRatedTVShows() } returns tvShowDataList
        runBlocking {
            topRatedTVShowsUseCaseImpl.topTVShows().let { list ->
                list shouldBe (1..3).map {
                    TopRatedTVShowDomain(
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
