package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.topTVShowsUseCase

val NAMED_TOP_TV_SHOWS_USE_CASE = named("NAMED_TOP_TV_SHOWS_USE_CASE")

val domainModule = module {
    single<TopTVShowsUseCase>(NAMED_TOP_TV_SHOWS_USE_CASE) { { page -> topTVShowsUseCase(page) } }
}
