package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RefreshFragmentImpl : RefreshFragment, LoadingFragment by LoadingFragmentImpl() {
    override fun refresh(lifecycleOwner: LifecycleOwner, callAndRefresh: suspend () -> Unit) {
        loading()
        lifecycleOwner.lifecycleScope.launch {
            callAndRefresh()
            loaded()
        }
    }
}
