package org.m0skit0.android.dabestmoviedbapp.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> withUseCaseContext(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO, block)
