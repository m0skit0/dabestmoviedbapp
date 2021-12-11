package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragmentImpl
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragmentImpl

val presentationModule = module {
    factory<FetchFragment<out Any>> { FetchFragmentImpl() }
    factory<ErrorFragment> { ErrorFragmentImpl() }
}
