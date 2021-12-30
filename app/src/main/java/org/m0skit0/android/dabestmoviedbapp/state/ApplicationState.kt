package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import java.io.Serializable

sealed class ApplicationState : Serializable

data class ShowDetailsState(
    val currentShowId: Long = -1L,
    val tvShowDetails: TVShowDetailsData? = null,
) : ApplicationState(), Serializable

infix fun ShowDetailsState.updateCurrentShowIdWith(newShowId: Long): ShowDetailsState =
    copy(currentShowId = newShowId)

infix fun ShowDetailsState.updateTvShowDetailsWith(newShow: TVShowDetailsData): ShowDetailsState =
    copy(tvShowDetails = newShow)

data class SimilarShowsState(
    val currentShowId: Long = -1L,
    val similarShows: List<SimilarTVShowData> = emptyList()
) : ApplicationState(), Serializable

infix fun SimilarShowsState.updateSimilarShowsWith(newList: List<SimilarTVShowData>): SimilarShowsState =
    copy(similarShows = newList)

data class TopRatedState(
    val currentPage: Int = 1,
    val topRatedShows: List<TopRatedTVShowData> = emptyList(),
    val genreMappingCache: Map<Int, String> = mapOf(),
) : ApplicationState(), Serializable

infix fun TopRatedState.updateGenreMappingCacheWith(newMap: Map<Int, String>): TopRatedState =
    copy(genreMappingCache = newMap)

infix fun TopRatedState.updateCurrentPageWith(newPage: Int): TopRatedState =
    copy(currentPage = newPage)

fun TopRatedState.updateWithNextPage(): TopRatedState = updateCurrentPageWith(currentPage.inc())

infix fun TopRatedState.updateTopRatedShowsWith(newList: List<TopRatedTVShowData>): TopRatedState =
    copy(topRatedShows = topRatedShows + newList)
