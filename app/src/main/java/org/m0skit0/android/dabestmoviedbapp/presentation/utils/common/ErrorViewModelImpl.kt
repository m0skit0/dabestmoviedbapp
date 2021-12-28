package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

// TODO Unit test
class ErrorViewModelImpl : ErrorViewModel {

    private lateinit var _error: MutableStateFlow<Boolean>
    private lateinit var viewModelScope: CoroutineScope

    override val error: Flow<Boolean> by lazy {
        _error.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000),
            false,
        )
    }

    override fun setMutableFlow(mutable: MutableStateFlow<Boolean>) {
        _error = mutable
    }

    override fun setViewModelScope(scope: CoroutineScope) {
        viewModelScope = scope
    }
}
