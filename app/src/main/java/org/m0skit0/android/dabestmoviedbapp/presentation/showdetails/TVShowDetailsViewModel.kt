package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.Error
import org.m0skit0.android.dabestmoviedbapp.presentation.Loading
import org.m0skit0.android.dabestmoviedbapp.presentation.Result
import org.m0skit0.android.dabestmoviedbapp.presentation.ViewState
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState

class TVShowDetailsViewModel(
    private val tvShowDetailsState: ShowDetailsState,
    private val tvShowDetailsUseCase: TVShowDetailsUseCase,
) : ViewModel() {

    private val _viewState: MutableState<ViewState> by lazy { mutableStateOf(Loading) }
    val viewState: State<ViewState> by lazy { _viewState.apply { load() } }

    private fun load() {
        viewModelScope.launch {
            tvShowDetailsUseCase(tvShowDetailsState).fold({
                it.printStackTrace()
                _viewState.value = Error
            }){ state ->
                _viewState.value = state.tvShowDetails
                    ?.toTVShowDetailsPresentation()
                    ?.let { Result(it) }
                    ?: Error
            }
        }
    }
}
