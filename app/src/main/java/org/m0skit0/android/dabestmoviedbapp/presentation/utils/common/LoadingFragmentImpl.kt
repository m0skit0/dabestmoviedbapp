package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import android.view.View
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.invisible
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.visible

// TODO Unit test
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
