package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET

interface TopRatedTVShowsService {
    // TODO Paginate
    @GET("tv/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun topRatedTVShows(): TopRatedTVShowsApi
}
