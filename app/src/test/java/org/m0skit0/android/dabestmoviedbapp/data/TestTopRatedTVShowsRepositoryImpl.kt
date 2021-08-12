package org.m0skit0.android.dabestmoviedbapp.data

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
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

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when top rated tv shows is an empty list, should return an empty list`() {
        every { mockTopRatedTVShowsService.topRatedTVShows() } returns flow { emit(TopRatedTVShowsApi()) }
        runBlocking {
            mockTopRatedTVShowsService.topRatedTVShows().collect {
                it.topRatedTVShows shouldBe emptyList<TopRatedTVShowApi>()
            }
        }
    }
}
