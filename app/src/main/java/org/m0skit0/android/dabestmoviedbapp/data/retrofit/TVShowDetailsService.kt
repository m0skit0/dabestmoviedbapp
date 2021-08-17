package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowDetailsService {
    @GET("tv/{id}")
    suspend fun tvShowDetails(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Path("page") id: Long,
    ): TVShowDetailsApi
}
