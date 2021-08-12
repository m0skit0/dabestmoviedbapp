package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import kotlinx.coroutines.flow.Flow
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET

interface TVGenreService {
    @GET("genre/tv/list?apiKey=${BuildConfig.API_KEY}")
    fun tvGenres(): Flow<TVGenresApi>
}
