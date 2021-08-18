package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.fragment.app.Fragment

fun interface ErrorFragment {
    fun setupErrorListener(fragment: Fragment, viewModel: ErrorViewModel)
}
