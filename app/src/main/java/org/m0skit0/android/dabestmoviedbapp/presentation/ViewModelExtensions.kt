package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import kotlinx.coroutines.launch

fun <T> ViewModel.load(
    useCase: suspend () -> Either<Throwable, T>,
    viewState: MutableState<ViewState>,
    viewStateResult: (T) -> ViewState
) {
    viewModelScope.launch {
        useCase().fold({
            it.printStackTrace()
            viewState.value = Error
        }){ state ->
            viewState.value = viewStateResult(state)
        }
    }
}
