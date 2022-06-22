package org.m0skit0.android.dabestmoviedbapp.di

//import org.m0skit0.android.dabestmoviedbapp.presentation.similarshows.SimilarTVShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopRatedListViewModel

val presentationModule = module {
    viewModel {
        TopRatedListViewModel(
            topRatedUseCase = get(NAMED_TOP_TV_SHOWS_USE_CASE)
        )
    }
//    viewModel {
//        TVShowDetailsViewModel(
//            tvShowDetailsUseCase = get(NAMED_TV_SHOW_DETAILS_USE_CASE),
//        )
//    }
//    viewModel { (similarShowsState: SimilarShowsState) ->
//        SimilarTVShowsViewModel(
//            similarShowsState = similarShowsState,
//            similarTVShowsUseCase = get(NAMED_SIMILAR_TV_SHOWS_USE_CASE),
//        )
//    }
}

//@Composable
//fun getSimilarTVShowsViewModel(tvShowId: Long): SimilarTVShowsViewModel =
//    SimilarShowsState(
//        currentShowId = tvShowId
//    ).let { getViewModel { parametersOf(it) } }
