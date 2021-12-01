package org.m0skit0.android.dabestmoviedbapp.di

import android.app.Application
import arrow.core.Either
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.cacheTVGenres
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.mapTVGenres
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.topTVShowsUseCase
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
}

val NAMED_TOP_RATED_TV_SHOWS_USECASE = named("NAMED_TOP_RATED_TV_SHOWS_USECASE")
private val domainModule = module {
    single<suspend (Int) -> Either<Throwable, List<TopRatedTVShowData>>>(NAMED_TOP_RATED_TV_SHOWS_USECASE) {
        { page -> topTVShowsUseCase(page) }
    }
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
}

val NAMED_TOP_RATED_TV_SHOWS_REPOSITORY = named("NAMED_TOP_RATED_TV_SHOWS_REPOSITORY")
private val repositoryModule = module {
    single<suspend (Int) -> Either<Throwable, List<TopRatedTVShowData>>>(NAMED_TOP_RATED_TV_SHOWS_REPOSITORY) {
        { page -> topRatedTVShowsRepository(page = page) }
    }
    single<suspend (ids: List<Int>) -> List<String>> {
        { ids ->
            cacheTVGenres()
            mapTVGenres(ids = ids)
        }
    }
}
