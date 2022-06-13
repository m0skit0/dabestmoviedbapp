package org.m0skit0.android.dabestmoviedbapp.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsService
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val retrofitModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
    factory<OkHttpClient> {
        OkHttpClient()
            .newBuilder()
            .apply {
                if (BuildConfig.LOG_HTTP_CALLS) addInterceptor(get<Interceptor>())
            }.build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(get())
            .build()
    }
    single<TopRatedTVShowsService> { get<Retrofit>().create(TopRatedTVShowsService::class.java) }
    single<TVGenreService> { get<Retrofit>().create(TVGenreService::class.java) }
    single<TVShowDetailsService> { get<Retrofit>().create(TVShowDetailsService::class.java) }
    single<SimilarTVShowsService> { get<Retrofit>().create(SimilarTVShowsService::class.java) }
}
