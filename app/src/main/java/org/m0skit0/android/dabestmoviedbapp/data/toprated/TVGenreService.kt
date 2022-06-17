package org.m0skit0.android.dabestmoviedbapp.data.toprated

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface TVGenreService {
    @GET("genre/tv/list")
    suspend fun tvGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): TVGenresDTO
}
