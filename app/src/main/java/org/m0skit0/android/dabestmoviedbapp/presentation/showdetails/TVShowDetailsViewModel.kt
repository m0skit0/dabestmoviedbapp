package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TVShowDetailsUseCase
) : ViewModel() {

    suspend fun tvShowDetails(id: Long): TVShowDetailsPresentation =
        tvShowDetailsUseCase.tvShowDetails(id).toTVShowDetailsPresentation()
}
