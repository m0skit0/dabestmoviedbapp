package org.m0skit0.android.dabestmoviedbapp.presentation.utils.common

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import arrow.core.Either
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.errorToast
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

class FetchFragmentImpl(
    loadingFragment: LoadingFragment = koin().get()
) : FetchFragment<ApplicationState>, LoadingFragment by loadingFragment {
    override fun Fragment.fetch(fetch: suspend () -> Either<Throwable, ApplicationState>, success: (ApplicationState) -> Unit) {
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
