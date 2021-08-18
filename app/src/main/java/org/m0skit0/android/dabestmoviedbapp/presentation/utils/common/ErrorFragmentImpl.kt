package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.toast

class ErrorFragmentImpl : ErrorFragment {
    override fun setupErrorListener(fragment: Fragment, viewModel: ErrorViewModel) {
        with(fragment) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.error.collect { isError ->
                    if (isError) toast(R.string.error_happened)
                }
            }
        }
    }
}
