package org.m0skit0.android.dabestmoviedbapp.presentation.utils

import android.view.View

class LoadingFragmentImpl : LoadingFragment {

    private lateinit var loadingView: View

    override fun loading() {
        loadingView.visible()
    }

    override fun loaded() {
        loadingView.invisible()
    }

    override fun setLoadingView(view: View) {
        loadingView = view
    }
}
