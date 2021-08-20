package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.stateInWhileSubscribed
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TVShowDetailsUseCase,
    errorViewModel: ErrorViewModel,
) : ViewModel(), ErrorViewModel by errorViewModel {

    private val _error = MutableStateFlow(false)

    private val _tvShowDetails by lazy { MutableStateFlow(TVShowDetailsPresentation()) }

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    fun tvShowDetails(id: Long): StateFlow<TVShowDetailsPresentation> =
        _tvShowDetails.stateInWhileSubscribed(
            viewModelScope,
            TVShowDetailsPresentation()
        ).apply {
            viewModelScope.launch {
                postTVShowDetails(id)
            }
        }

    private suspend fun postTVShowDetails(id: Long) {
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
}
