package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopRatedTVShowsUseCase
import javax.inject.Inject

@HiltViewModel
class TopRatedTVShowsViewModel
@Inject constructor(
    private val topRatedTVShowsUseCase: TopRatedTVShowsUseCase
) : ViewModel() {

    private var currentPage = 1

    private var currentTVShowList: List<TopRatedTVShowsItem> = emptyList()

    private var _error = MutableStateFlow(false)

    val error: StateFlow<Boolean> = _error.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
        false,
    )

    suspend fun topRatedShows(): List<TopRatedTVShowsItem> =
        try {
            topRatedTVShowsUseCase.topTVShows(currentPage).apply {
                _error.value = false
            }
        } catch (e: Exception) {
            Log.e("topRatedShows", "Error", e)
            _error.value = true
            emptyList()
        }.apply {
            if (isNotEmpty()) currentPage++
        }.map {
            it.toTopRatedListingItem()
        }.let { nextPage ->
            currentTVShowList = currentTVShowList + nextPage
            currentTVShowList
        }
}
