package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TVShowDetailsUseCase
) : ViewModel() {

    val error: Flow<Boolean> = MutableStateFlow(false).shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
    )

    suspend fun tvShowDetails(id: Long): TVShowDetailsPresentation =
        tvShowDetailsUseCase.tvShowDetails(id).toTVShowDetailsPresentation()
}
