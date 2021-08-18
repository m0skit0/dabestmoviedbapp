package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TVShowDetailsUseCase
) : ViewModel(), ErrorViewModel by ErrorViewModelImpl() {

    private var _error = MutableStateFlow(false)

    init {
        setMutableFlow(_error)
        setViewModelScope(viewModelScope)
    }

    suspend fun tvShowDetails(id: Long): TVShowDetailsPresentation =
        tvShowDetailsUseCase.tvShowDetails(id).toTVShowDetailsPresentation()
}
