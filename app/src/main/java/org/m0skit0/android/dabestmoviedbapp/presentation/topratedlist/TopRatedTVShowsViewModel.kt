package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.m0skit0.android.dabestmoviedbapp.domain.TopRatedTVShowsUseCase
import javax.inject.Inject

@HiltViewModel
class TopRatedTVShowsViewModel
@Inject constructor(
    private val topRatedTVShowsUseCase: TopRatedTVShowsUseCase
) : ViewModel() {

    val topRatedShows: StateFlow<List<TopRatedTVShowsItem>> =
        topRatedTVShowsUseCase.topTVShows()
            .map { list ->
                list.map { it.toTopRatedListingItem() }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
}
