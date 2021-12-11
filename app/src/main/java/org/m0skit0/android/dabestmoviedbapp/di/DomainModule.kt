package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.tvShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.topTVShowsUseCase

val NAMED_TOP_TV_SHOWS_USE_CASE = named("NAMED_TOP_TV_SHOWS_USE_CASE")
val NAMED_TV_SHOW_DETAILS_USE_CASE = named("NAMED_TV_SHOW_DETAILS_USE_CASE")
val domainModule = module {
    single<TopTVShowsUseCase>(NAMED_TOP_TV_SHOWS_USE_CASE) { { page -> topTVShowsUseCase(page) } }
    single<TVShowDetailsUseCase>(NAMED_TV_SHOW_DETAILS_USE_CASE) { { id -> tvShowDetailsUseCase(id = id) } }
}
