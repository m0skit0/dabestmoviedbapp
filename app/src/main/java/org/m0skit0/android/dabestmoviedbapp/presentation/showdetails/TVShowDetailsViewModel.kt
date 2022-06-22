//package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails
//
//import androidx.lifecycle.ViewModel
//import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
//import org.m0skit0.android.dabestmoviedbapp.presentation.Failure
//import org.m0skit0.android.dabestmoviedbapp.presentation.ViewState
//import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState
//
//class TVShowDetailsViewModel(
//    private val tvShowDetailsUseCase: TVShowDetailsUseCase,
//) : ViewModel() {
//    suspend fun load(id: Long): ViewState =
//        tvShowDetailsUseCase(ShowDetailsState(currentShowId = id)).fold({state ->
//            state.tvShowDetails
//                ?.toTVShowDetailsPresentation()
//                ?.let { Result(it) }
//                ?: Failure
//        }) {
//            it.printStackTrace()
//            Failure
//        }
//}
