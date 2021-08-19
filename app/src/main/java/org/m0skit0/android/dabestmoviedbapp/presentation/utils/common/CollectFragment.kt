package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.StateFlow

interface CollectFragment<T> : LoadingFragment {
    fun StateFlow<T>.collect(lifecycleOwner: LifecycleOwner, onCollect: suspend (T) -> Unit)
}
