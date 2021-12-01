package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import arrow.core.Either
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.errorToast

class FetchFragmentImpl<T> : FetchFragment<T>, LoadingFragment by LoadingFragmentImpl() {
    override fun Fragment.fetch(fetch: suspend () -> Either<Throwable, T>, success: (T) -> Unit) {
        loading()
        lifecycleScope.launch {
            fetch().fold({
                Log.e("ERROR", "Error", it)
                errorToast()
            }) {
                success(it)
                loaded()
            }
        }
    }
}