package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*

val NAMED_FETCH_FRAGMENT_DEFAULT = named("NAMED_FETCH_FRAGMENT_DEFAULT")
val NAMED_FETCH_FRAGMENT_NO_LOADING = named("NAMED_FETCH_FRAGMENT_NO_LOADING")

val presentationModule = module {
    factory<FetchFragment<out Any>>(NAMED_FETCH_FRAGMENT_DEFAULT) { FetchFragmentImpl() }
    factory<FetchFragment<out Any>>(NAMED_FETCH_FRAGMENT_NO_LOADING) { FetchFragmentImpl(EmptyLoadingFragment()) }
    factory<LoadingFragment> { LoadingFragmentImpl() }
}
