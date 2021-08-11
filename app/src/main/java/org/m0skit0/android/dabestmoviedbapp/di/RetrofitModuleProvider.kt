package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVGenreService
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModuleProvider {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideTopRatedTVShowsService(retrofit: Retrofit): TopRatedTVShowsService =
        retrofit.create(TopRatedTVShowsService::class.java)

    @Provides
    @Singleton
    fun provideTVGenresService(retrofit: Retrofit): TVGenreService =
        retrofit.create(TVGenreService::class.java)
}
