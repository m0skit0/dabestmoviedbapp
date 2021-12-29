package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import java.io.Serializable

data class ApplicationState(
    val topRatedState: TopRatedState = TopRatedState(),
    val showDetailsState: ShowDetailsState = ShowDetailsState(),
    val similarShowsState: SimilarShowsState = SimilarShowsState()
) : Serializable

infix fun ApplicationState.updateGenreMappingCacheWith(newMap: Map<Int, String>): ApplicationState =
    copy(topRatedState = topRatedState.copy(genreMappingCache = newMap))

infix fun ApplicationState.updateCurrentPageWith(newPage: Int): ApplicationState =
    copy(topRatedState = topRatedState.copy(currentPage = newPage))

fun ApplicationState.updateWithNextPage(): ApplicationState = updateCurrentPageWith(topRatedState.currentPage + 1)

infix fun ApplicationState.updateCurrentShowIdWith(newShowId: Long): ApplicationState =
    copy(showDetailsState = showDetailsState.copy(currentShowId = newShowId))

infix fun ApplicationState.updateTopRatedShowsWith(newList: List<TopRatedTVShowData>): ApplicationState =
    copy(topRatedState = topRatedState.copy(topRatedShows = topRatedState.topRatedShows + newList))

infix fun ApplicationState.updateTvShowDetailsWith(newShow: TVShowDetailsData): ApplicationState =
    copy(showDetailsState = showDetailsState.copy(tvShowDetails = newShow))

infix fun ApplicationState.updateSimilarShowsWith(newList: List<SimilarTVShowData>): ApplicationState =
    copy(similarShowsState = similarShowsState.copy(similarShows = newList))
