package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO Unit test
class CollectFragmentImpl<T>(
    loadingFragment: LoadingFragment = LoadingFragmentImpl()
) : CollectFragment<T>, LoadingFragment by loadingFragment {

    override fun StateFlow<T>.collect(
        lifecycleOwner: LifecycleOwner,
        onCollect: suspend (T) -> Boolean
    ) {
        loading()
        lifecycleOwner.lifecycleScope.launch {
            collect {
                onCollect(it).let { isOk ->
                    if (isOk) loaded()
                }
            }
        }
    }
}
