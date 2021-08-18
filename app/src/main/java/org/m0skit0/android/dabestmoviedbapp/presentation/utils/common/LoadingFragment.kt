package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import android.view.View

interface LoadingFragment {
    fun setLoadingView(view: View)
    fun loading()
    fun loaded()
}
