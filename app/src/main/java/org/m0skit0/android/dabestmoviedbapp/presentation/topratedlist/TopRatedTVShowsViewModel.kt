package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import org.m0skit0.android.dabestmoviedbapp.domain.TopRatedTVShowsUseCase
import javax.inject.Inject

@HiltViewModel
class TopRatedTVShowsViewModel
@Inject constructor(
    private val topRatedTVShowsUseCase: TopRatedTVShowsUseCase
) : ViewModel() {

    private var currentPage = 1

    private var currentTVShowList: List<TopRatedTVShowsItem> = emptyList()

    val error: Flow<Boolean> = MutableStateFlow(false).shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
    )

    suspend fun topRatedShows(): List<TopRatedTVShowsItem> =
        try {
            topRatedTVShowsUseCase.topTVShows(currentPage)
        } catch (e: Exception) {
            Log.e("topRatedShows", "Error", e)
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
