package org.m0skit0.android.dabestmoviedbapp.di

import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsService
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenreService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
    single<TopRatedTVShowsService> { get<Retrofit>().create(TopRatedTVShowsService::class.java) }
    single<TVGenreService> { get<Retrofit>().create(TVGenreService::class.java) }
    single<TVShowDetailsService> { get<Retrofit>().create(TVShowDetailsService::class.java) }
    single<SimilarTVShowsService> { get<Retrofit>().create(SimilarTVShowsService::class.java) }
}
