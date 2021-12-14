//package org.m0skit0.android.dabestmoviedbapp.data.tvgenres
//
//import io.mockk.MockKAnnotations
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.impl.annotations.MockK
//import kotlinx.coroutines.runBlocking
//import org.junit.Before
//import org.junit.Test
//import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenreApi
//import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenreService
//import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenresApi
//
//class TestTVGenreMapperImpl {
//
//    @MockK
//    private lateinit var mockTvGenreService: TVGenreService
//
//    private val tvGenreMapperImpl: TVGenreMapperImpl
//        get() = TVGenreMapperImpl(mockTvGenreService)
//
//    @Before
//    fun setup() {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun `when mapping an empty list of genres against an empty genre API, mapping should also return an empty list`() {
//        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi()
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(emptyList()).shouldBeEmpty()
//        }
//    }
//
//    @Test
//    fun `when mapping a non-empty list of genres against an empty genre API, mapping should also return an empty list`() {
//        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi()
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(listOf(1, 2, 3)).shouldBeEmpty()
//        }
//    }
//
//    @Test
//    fun `when mapping a empty list of genres against a non-empty genre API, mapping should also return an empty list`() {
//        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi(
//            tVGenres = listOf(TVGenreApi(name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1"), TVGenreApi(name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2"))
//        )
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(emptyList()).shouldBeEmpty()
//        }
//    }
//
//    @Test
//    fun `when mapping a valid non-empty list of genres against a non-empty genre API, mapping should be done correctly`() {
//        coEvery { mockTvGenreService.tvGenres() } returns
//                TVGenresApi(
//                    tVGenres = listOf(
//                        TVGenreApi(id = 1, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1"),
//                        TVGenreApi(id = 2, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2"),
//                        TVGenreApi(id = 3, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 3"),
//                    )
//                )
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(listOf(1, 2)) shouldBe listOf("org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1", "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2")
//        }
//    }
//
//    @Test
//    fun `when mapping an invalid list of genres against a genre API, mapping should ignore invalid genre ids`() {
//        coEvery { mockTvGenreService.tvGenres() } returns
//                TVGenresApi(
//                    tVGenres = listOf(
//                        TVGenreApi(id = 1, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1"),
//                        TVGenreApi(id = 2, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2")
//                    )
//                )
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(listOf(2, 4)) shouldBe listOf("org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2")
//        }
//    }
//
//    @Test
//    fun `when mapping a list of genres against a genre API with no intersection, mapping should return an empty list`() {
//        coEvery { mockTvGenreService.tvGenres() } returns
//                TVGenresApi(
//                    tVGenres = listOf(
//                        TVGenreApi(id = 1, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1"),
//                        TVGenreApi(id = 2, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2")
//                    )
//                )
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(listOf(3, 4)) shouldBe emptyList()
//        }
//    }
//
//    @Test
//    fun `when mapping, it should call the API on the first call`() {
//        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi()
//        runBlocking {
//            tvGenreMapperImpl.mapGenres(emptyList())
//        }
//        coVerify {
//            mockTvGenreService.tvGenres()
//        }
//    }
//
//    @Test
//    fun `when mapping, it should call API repeatedly if no genres were resolved`() {
//        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi()
//        runBlocking {
//            tvGenreMapperImpl.run {
//                repeat(5) {
//                    mapTVGenres(emptyList())
//                }
//            }
//        }
//        coVerify(exactly = 5) {
//            mockTvGenreService.tvGenres()
//        }
//    }
//
//    @Test
//    fun `when mapping, it should only call API on the first call if genres were resolved`() {
//        coEvery { mockTvGenreService.tvGenres() } returns TVGenresApi(
//            tVGenres = listOf(
//                TVGenreApi(id = 1, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 1"),
//                TVGenreApi(id = 2, name = "org.m0skit0.android.dabestmoviedbapp.data.showdetails.Genre 2")
//            )
//        )
//        runBlocking {
//            tvGenreMapperImpl.run {
//                repeat(5) {
//                    mapTVGenres(emptyList())
//                }
//            }
//        }
//        coVerify(atLeast = 1, atMost = 1) {
//            mockTvGenreService.tvGenres()
//        }
//    }
//}
