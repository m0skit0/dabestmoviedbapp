package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import androidx.fragment.app.Fragment
import arrow.core.Either

interface FetchFragment<T> : LoadingFragment {
    fun Fragment.fetch(fetch: suspend () -> Either<Throwable, T>, success: (T) -> Unit)
}
