package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CollectFragmentImpl<T> : CollectFragment<T>, LoadingFragment by LoadingFragmentImpl() {

    override fun StateFlow<T>.collect(
        lifecycleOwner: LifecycleOwner,
        onCollect: suspend (T) -> Unit
    ) {
        loading()
        lifecycleOwner.lifecycleScope.launch {
            collect {
                onCollect(it)
                loaded()
            }
        }
    }
}
