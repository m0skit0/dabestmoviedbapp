package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.tvShowDetails
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.similarTVShows
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository

val NAMED_TOP_TV_SHOWS_REPOSITORY = named("NAMED_TOP_TV_SHOWS_REPOSITORY")
val NAMED_TV_SHOW_DETAILS_REPOSITORY = named("NAMED_TV_SHOW_DETAILS_REPOSITORY")
val NAMED_SIMILAR_TV_SHOWS_REPOSITORY = named("NAMED_SIMILAR_TV_SHOWS_REPOSITORY")

val repositoryModule = module {
    single<TopRatedTVShowsRepository>(NAMED_TOP_TV_SHOWS_REPOSITORY) {
        { state -> topRatedTVShowsRepository(state = state) }
    }
    single<TVShowDetailsRepository>(NAMED_TV_SHOW_DETAILS_REPOSITORY) {
        { state -> tvShowDetails(state = state) }
    }
    single<SimilarTVShowsRepository>(NAMED_SIMILAR_TV_SHOWS_REPOSITORY) {
        { state -> similarTVShows(state = state) }
    }
}
