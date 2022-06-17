package org.m0skit0.android.dabestmoviedbapp.di

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailsViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.similarshows.SimilarTVShowsViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopRatedListViewModel
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState
import org.m0skit0.android.dabestmoviedbapp.state.SimilarShowsState

val presentationModule = module {
    viewModel {
        TopRatedListViewModel(
            topRatedUseCase = get(NAMED_TOP_TV_SHOWS_USE_CASE)
        )
    }
    viewModel { (tvShowDetailsState: ShowDetailsState) ->
        TVShowDetailsViewModel(
            tvShowDetailsState = tvShowDetailsState,
            tvShowDetailsUseCase = get(NAMED_TV_SHOW_DETAILS_USE_CASE),
        )
    }
    viewModel { (similarShowsState: SimilarShowsState) ->
        SimilarTVShowsViewModel(
            similarShowsState = similarShowsState,
            similarTVShowsUseCase = get(NAMED_SIMILAR_TV_SHOWS_USE_CASE),
        )
    }
}

@Composable
fun getTVShowDetailsViewModel(tvShowId: Long): TVShowDetailsViewModel =
    ShowDetailsState(
        currentShowId = tvShowId
    ).let { getViewModel { parametersOf(it) } }

@Composable
fun getSimilarTVShowsViewModel(tvShowId: Long): SimilarTVShowsViewModel =
    SimilarShowsState(
        currentShowId = tvShowId
    ).let { getViewModel { parametersOf(it) } }
