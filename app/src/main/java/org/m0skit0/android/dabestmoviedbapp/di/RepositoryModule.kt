package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.tvShowDetails
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenresRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.cacheTVGenres
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.mapTVGenres

val NAMED_TOP_TV_SHOWS_REPOSITORY = named("NAMED_TOP_TV_SHOWS_REPOSITORY")
val NAMED_TV_SHOW_DETAILS_REPOSITORY = named("NAMED_TV_SHOW_DETAILS_REPOSITORY")
val repositoryModule = module {
    single<TopRatedTVShowsRepository>(NAMED_TOP_TV_SHOWS_REPOSITORY) {
        { page -> topRatedTVShowsRepository(page = page) }
    }
    single<TVGenresRepository> {
        { ids ->
            cacheTVGenres()
            mapTVGenres(ids = ids)
        }
    }
    single<TVShowDetailsRepository>(NAMED_TV_SHOW_DETAILS_REPOSITORY) {
        { id -> tvShowDetails(id = id) }
    }
}