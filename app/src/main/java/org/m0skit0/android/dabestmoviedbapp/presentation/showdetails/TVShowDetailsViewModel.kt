package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TVShowDetailsUseCase,
    private val similarTVShowUseCase: SimilarTVShowUseCase,
) : ViewModel(), ErrorViewModel by ErrorViewModelImpl() {

    private var currentId: Long = -1

    private var shows: List<Long> = emptyList()

    private var _error = MutableStateFlow(false)

    val similarShowsSize: Int
        get() = shows.size

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    suspend fun initialId(id: Long) {
        currentId = id
        loadShows(id)
    }

    suspend fun tvShowDetails(id: Long): TVShowDetailsPresentation = run {
        currentId = id
        try {
            tvShowDetailsUseCase.tvShowDetails(id).toTVShowDetailsPresentation().apply {
                _error.value = false
            }
        } catch (e: Exception) {
            Log.e("topRatedShows", "Error", e)
            _error.value = true
            TVShowDetailsPresentation()
        }
    }

    private suspend fun loadShows(id: Long) {
        shows = listOf(currentId) + similarTVShowUseCase.similarTVShows(id).map { it.id }
    }

    fun getShowInPosition(position: Int): Long = shows.getOrElse(position) { currentId }
}