package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ErrorViewModel {
    val error: Flow<Boolean>
    fun setMutableFlow(mutable: MutableStateFlow<Boolean>)
    fun setViewModelScope(scope: CoroutineScope)
}
