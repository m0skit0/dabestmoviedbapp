package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <USE_CASE_DATA, VIEW_DATA> ViewModel.load(
    currentState: VIEW_DATA,
    useCase: suspend () -> Result<USE_CASE_DATA>,
    onSuccess: (USE_CASE_DATA) -> Unit,
    mapping: (USE_CASE_DATA) -> VIEW_DATA,
    viewState: MutableState<StateResult<VIEW_DATA>>,
) {
    viewModelScope.launch {
        useCase()
            .onSuccess(onSuccess)
            .map(mapping)
            .fold(
                onSuccess = { value ->
                    viewState.value = StateResult(Success, value)
                },
                onFailure = {
                    viewState.value = StateResult(Failure, currentState)
                },
            )
    }
}
