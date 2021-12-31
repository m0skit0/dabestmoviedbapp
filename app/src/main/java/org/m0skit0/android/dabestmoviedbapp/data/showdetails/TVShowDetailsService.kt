package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowDetailsService {
    @GET("tv/{id}")
    suspend fun tvShowDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): TVShowDetailsApi
}
