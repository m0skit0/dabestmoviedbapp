package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopRatedTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl
import javax.inject.Inject

@HiltViewModel
class TopRatedTVShowsViewModel
@Inject constructor(
    private val topRatedTVShowsUseCase: TopRatedTVShowsUseCase
) : ViewModel(), ErrorViewModel by ErrorViewModelImpl() {

    private var nextPage = 1

    private var currentTVShowList: List<TopRatedTVShowsItem> = emptyList()

    private val _tvShowList: MutableStateFlow<List<TopRatedTVShowsItem>> by lazy { MutableStateFlow(emptyList()) }

    val tvShowList: StateFlow<List<TopRatedTVShowsItem>> by lazy {
        _tvShowList.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        ).apply {
            viewModelScope.launch {
                nextPage()
            }
        }
    }

    private var _error = MutableStateFlow(false)

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    suspend fun nextPage() {
        try {
            topRatedTVShowsUseCase.topTVShows(nextPage).apply {
                _error.value = false
            }
        } catch (e: Exception) {
            Log.e("topRatedShows", "Error", e)
            _error.value = true
            emptyList()
        }.apply {
            if (isNotEmpty()) nextPage++
        }.map {
            it.toTopRatedListingItem()
        }.let { nextPage ->
            currentTVShowList = currentTVShowList + nextPage
            _tvShowList.value = currentTVShowList
        }
    }
}
