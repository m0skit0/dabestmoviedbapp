package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.Loading
import org.m0skit0.android.dabestmoviedbapp.presentation.Result
import org.m0skit0.android.dabestmoviedbapp.presentation.ViewState
import org.m0skit0.android.dabestmoviedbapp.presentation.load
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState

class TopRatedListViewModel(
    private val topRatedUseCase: TopTVShowsUseCase
) : ViewModel() {

    private var topRatedState: TopRatedState = TopRatedState()

    private val _viewState: MutableState<ViewState> by lazy { mutableStateOf(Loading) }
    val viewState: State<ViewState> by lazy { _viewState.apply { load() } }

    fun checkAndTriggerNextPageLoading(index: Int) {
        if (topRatedState.topRatedShows.lastIndex - NEXT_PAGE_INTERVAL == index) {
            topRatedState = topRatedState.copy(currentPage = topRatedState.currentPage.inc())
            load()
        }
    }

    private fun load() {
        load(
            useCase = { topRatedUseCase(topRatedState) },
            viewState = _viewState,
            viewStateResult = { state ->
                topRatedState = state
                Result(state.topRatedShows.map { it.toTopRatedListingItem() })
            }
        )
    }

    companion object {
        private const val NEXT_PAGE_INTERVAL = 5
    }
}
