package org.m0skit0.android.dabestmoviedbapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVShowDetailsService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.tvShowDetails
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.cacheTVGenres
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.mapTVGenres
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.tvShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.topTVShowsUseCase
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
}

private val domainModule = module {
    single<TopTVShowsUseCase> { { page -> topTVShowsUseCase(page) } }
    single<TVShowDetailsUseCase> { { id -> tvShowDetailsUseCase(id = id) } }
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

private val repositoryModule = module {
    single<TopRatedTVShowsRepository> {
        { page -> topRatedTVShowsRepository(page = page) }
    }
    single<suspend (ids: List<Int>) -> List<String>> {
        { ids ->
            cacheTVGenres()
            mapTVGenres(ids = ids)
        }
    }
    single<TVShowDetailsRepository>  {
        { id -> tvShowDetails(id = id) }
    }
}
