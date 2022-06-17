package org.m0skit0.android.dabestmoviedbapp.presentation.similarshows

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.*
import org.m0skit0.android.dabestmoviedbapp.state.SimilarShowsState

class SimilarTVShowsViewModel(
    private val similarShowsState: SimilarShowsState,
    private val similarTVShowsUseCase: SimilarTVShowsUseCase,
) : ViewModel() {

    private val _viewState: MutableState<ViewState> by lazy { mutableStateOf(Loading) }
    val viewState: State<ViewState> by lazy {
        _viewState.apply {
            load(
                useCase = { similarTVShowsUseCase(similarShowsState) },
                viewState = _viewState,
                viewStateResult = { state ->
                    state.similarShows
                    .map { it.id }
                    .let { if (it.isEmpty()) Error else Result(it) }
                }
            )
        }
    }
}
