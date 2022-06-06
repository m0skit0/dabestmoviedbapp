package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopRatedListViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopRatedStateHolder

val presentationModule = module {
    single { TopRatedStateHolder() }
    viewModel {
        TopRatedListViewModel(
            topRatedUseCase = get()
        )
    }
}
