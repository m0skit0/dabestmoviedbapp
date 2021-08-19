package org.m0skit0.android.dabestmoviedbapp.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

private const val SUBSCRIBE_TIMEOUT = 5000L
fun <T> StateFlow<T>.stateInWhileSubscribed(scope: CoroutineScope, defaultValue: T) = stateIn(
    scope,
    SharingStarted.WhileSubscribed(SUBSCRIBE_TIMEOUT),
    defaultValue
)
