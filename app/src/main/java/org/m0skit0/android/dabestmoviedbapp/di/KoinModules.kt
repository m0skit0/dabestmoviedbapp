package org.m0skit0.android.dabestmoviedbapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVShowDetailsService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.tvShowDetails
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenresRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.cacheTVGenres
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.mapTVGenres
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.tvShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.topTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragmentImpl
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragmentImpl
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

private lateinit var koinApplication: KoinApplication

fun Application.startKoin() {
    koinApplication = startKoin {
        androidLogger()
        androidContext(this@startKoin)
        modules(
            presentationModule,
            domainModule,
            retrofitModule,
            repositoryModule,
        )
    }
}

fun koin(): Koin = koinApplication.koin

private val presentationModule = module {
    factory<FetchFragment<out Any>> { FetchFragmentImpl() }
    factory<ErrorFragment> { ErrorFragmentImpl() }
}

val NAMED_TOP_TV_SHOWS_USE_CASE = named("NAMED_TOP_TV_SHOWS_USE_CASE")
val NAMED_TV_SHOW_DETAILS_USE_CASE = named("NAMED_TV_SHOW_DETAILS_USE_CASE")
private val domainModule = module {
    single<TopTVShowsUseCase>(NAMED_TOP_TV_SHOWS_USE_CASE) { { page -> topTVShowsUseCase(page) } }
    single<TVShowDetailsUseCase>(NAMED_TV_SHOW_DETAILS_USE_CASE) { { id -> tvShowDetailsUseCase(id = id) } }
}

private val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
    single<TopRatedTVShowsService> { get<Retrofit>().create(TopRatedTVShowsService::class.java) }
    single<TVGenreService> { get<Retrofit>().create(TVGenreService::class.java) }
    single<TVShowDetailsService> { get<Retrofit>().create(TVShowDetailsService::class.java) }
}

val NAMED_TOP_TV_SHOWS_REPOSITORY = named("NAMED_TOP_TV_SHOWS_REPOSITORY")
val NAMED_TV_SHOW_DETAILS_REPOSITORY = named("NAMED_TV_SHOW_DETAILS_REPOSITORY")
private val repositoryModule = module {
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
