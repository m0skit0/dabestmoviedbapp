package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.stateInWhileSubscribed
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsPagerViewModel @Inject constructor(
    private val similarTVShowUseCase: SimilarTVShowUseCase,
) : ViewModel(), ErrorViewModel by ErrorViewModelImpl() {

    private val _error by lazy { MutableStateFlow(false) }

    private val _shows: MutableStateFlow<List<Long>> by lazy { MutableStateFlow(emptyList()) }

    val shows: StateFlow<List<Long>> by lazy {
        _shows.stateInWhileSubscribed(viewModelScope, emptyList())
    }

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    suspend fun loadShows(id: Long) {
        _shows.value = listOf(id) + try {
            similarTVShowUseCase.similarTVShows(id).map { it.id }.apply {
                _error.value = false
            }
        } catch (e: Exception) {
            Log.e("loadShows", "Error", e)
            _error.value = true
            emptyList()
        }
    }
}
