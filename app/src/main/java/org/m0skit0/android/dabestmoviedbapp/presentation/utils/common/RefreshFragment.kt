package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.lifecycle.LifecycleOwner

interface RefreshFragment : LoadingFragment {
    fun refresh(lifecycleOwner: LifecycleOwner, callAndRefresh: suspend () -> Unit)
}
