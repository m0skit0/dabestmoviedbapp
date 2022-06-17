package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository

val NAMED_TOP_TV_SHOWS_REPOSITORY = named("NAMED_TOP_TV_SHOWS_REPOSITORY")

val repositoryModule = module {
    single<TopRatedTVShowsRepository>(NAMED_TOP_TV_SHOWS_REPOSITORY) {
        { state -> topRatedTVShowsRepository(state = state) }
    }
}
