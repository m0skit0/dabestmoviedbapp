package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.Result

fun <T> ViewModel.load(
    useCase: suspend () -> Result<T>,
    viewState: MutableState<ViewState>,
    viewStateResult: (T) -> ViewState
) {
    viewModelScope.launch {
        useCase().fold({ state ->
            viewState.value = viewStateResult(state)
        }){
            it.printStackTrace()
            viewState.value = Error
        }
    }
}
