package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.stateInWhileSubscribed
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TVShowDetailsUseCase,
    private val similarTVShowUseCase: SimilarTVShowUseCase,
) : ViewModel(), ErrorViewModel by ErrorViewModelImpl() {

    private var shows: List<Long> = emptyList()

    private var _error = MutableStateFlow(false)

    private val _tvShowDetails by lazy { MutableStateFlow(TVShowDetailsPresentation()) }

    val tvShowDetails: StateFlow<TVShowDetailsPresentation> by lazy {
        _tvShowDetails.stateInWhileSubscribed(
            viewModelScope,
            TVShowDetailsPresentation()
        )
    }

    val similarShowsSize: Int
        get() = shows.size

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    suspend fun loadShows(id: Long) {
        shows = listOf(id) + similarTVShowUseCase.similarTVShows(id).map { it.id }
    }

    suspend fun tvShowDetails(id: Long) {
        try {
            tvShowDetailsUseCase.tvShowDetails(id).toTVShowDetailsPresentation().apply {
                _error.value = false
            }
        } catch (e: Exception) {
            Log.e("tvShowDetails", "Error", e)
            _error.value = true
            TVShowDetailsPresentation()
        }.let {
            _tvShowDetails.value = it
        }
    }

    fun getShowInPosition(position: Int): Long = shows.getOrElse(position) { -1 }
}
