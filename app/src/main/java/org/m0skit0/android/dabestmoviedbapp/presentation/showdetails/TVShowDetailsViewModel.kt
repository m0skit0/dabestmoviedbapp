package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.*
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState

class TVShowDetailsViewModel(
    private val tvShowDetailsState: ShowDetailsState,
    private val tvShowDetailsUseCase: TVShowDetailsUseCase,
) : ViewModel() {

    private val _viewState: MutableState<ViewState> by lazy { mutableStateOf(Loading) }
    val viewState: State<ViewState> by lazy {
        _viewState.apply {
            load(
                useCase = { tvShowDetailsUseCase(tvShowDetailsState) },
                viewState = _viewState,
                viewStateResult = { state ->
                    state.tvShowDetails
                        ?.toTVShowDetailsPresentation()
                        ?.let { Result(it) }
                        ?: Error
                }
            )
        }
    }
}
