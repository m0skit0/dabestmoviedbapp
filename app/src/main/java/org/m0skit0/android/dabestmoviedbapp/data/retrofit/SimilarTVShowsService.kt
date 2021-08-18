package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarTVShowsService {
    // TODO Support other pages
    @GET("tv/{id}/similar")
    suspend fun similarTVShows(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): SimilarTVShowsApi
}
