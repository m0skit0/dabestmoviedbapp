package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.stateInWhileSubscribed

class TopRatedTVShowsViewModel
constructor(
    private val topRatedTVShowsUseCase: suspend (page: Int) -> List<TopRatedTVShowData> = koin().get(),
    errorViewModel: ErrorViewModel,
) : ViewModel(), ErrorViewModel by errorViewModel {

    private var nextPage = 1

    private var currentTVShowList: List<TopRatedTVShowsItem> = emptyList()

    private val _tvShowList: MutableStateFlow<List<TopRatedTVShowsItem>> by lazy {
        MutableStateFlow(
            emptyList()
        )
    }

    val tvShowList: StateFlow<List<TopRatedTVShowsItem>> by lazy {
        _tvShowList.stateInWhileSubscribed(
            viewModelScope,
            emptyList()
        ).apply {
            viewModelScope.launch {
                nextPage()
            }
        }
    }

    private val _error by lazy { MutableStateFlow(false) }

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    suspend fun nextPage() {
        try {
            topRatedTVShowsUseCase(nextPage).apply {
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
