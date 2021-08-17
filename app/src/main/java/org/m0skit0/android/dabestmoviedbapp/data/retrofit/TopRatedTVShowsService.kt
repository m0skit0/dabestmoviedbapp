package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedTVShowsService {
    @GET("tv/top_rated")
    suspend fun topRatedTVShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1,
    ): TopRatedTVShowsApi
}
