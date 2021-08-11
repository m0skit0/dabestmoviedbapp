package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import kotlinx.coroutines.flow.Flow
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET

interface TopRatedTVShowsService {
    // TODO Paginate
    @GET("tv/top_rated?apiKey=${BuildConfig.API_KEY}")
    fun topRatedTVShows(): Flow<TopRatedTVShows>
}
