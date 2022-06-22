package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.Loading
import org.m0skit0.android.dabestmoviedbapp.presentation.StateResult
import org.m0skit0.android.dabestmoviedbapp.presentation.load
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState

class TopRatedListViewModel(
    private val topRatedUseCase: TopTVShowsUseCase
) : ViewModel() {

    private var topRatedState: TopRatedState = TopRatedState()

    private val _viewState: MutableState<StateResult<List<TopRatedTVShowView>>> by lazy {
        mutableStateOf(StateResult(Loading, emptyList()))
    }
    val viewState: State<StateResult<List<TopRatedTVShowView>>> by lazy { _viewState.apply { load() } }

    fun checkAndTriggerNextPageLoading(index: Int) {
        if (topRatedState.topRatedShows.lastIndex - NEXT_PAGE_INTERVAL == index) {
            topRatedState = topRatedState.copy(currentPage = topRatedState.currentPage.inc())
            load()
        }
    }

    private fun load() {
        load(
            currentState = topRatedState.toTopRatedListingItems(),
            useCase = { topRatedUseCase(topRatedState) },
            mapping = { it.toTopRatedListingItems() },
            viewState = _viewState
        )
    }

    private fun TopRatedState.toTopRatedListingItems(): List<TopRatedTVShowView> =
        topRatedShows.map { it.toTopRatedListingItem() }

    companion object {
        private const val NEXT_PAGE_INTERVAL = 5
    }
}
