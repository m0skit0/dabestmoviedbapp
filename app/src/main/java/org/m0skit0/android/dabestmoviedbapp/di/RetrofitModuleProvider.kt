package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.SimilarTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVShowDetailsService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModuleProvider {

    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideTopRatedTVShowsService(retrofit: Retrofit): TopRatedTVShowsService =
        retrofit.create(TopRatedTVShowsService::class.java)

    @Provides
    @Singleton
    fun provideTVGenresService(retrofit: Retrofit): TVGenreService =
        retrofit.create(TVGenreService::class.java)

    @Provides
    @Singleton
    fun provideTVShowDetailsService(retrofit: Retrofit): TVShowDetailsService =
        retrofit.create(TVShowDetailsService::class.java)

    @Provides
    @Singleton
    fun provideSimilarTVShowsService(retrofit: Retrofit): SimilarTVShowsService =
        retrofit.create(SimilarTVShowsService::class.java)
}
