package org.m0skit0.android.dabestmoviedbapp.data.retrofit

import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import retrofit2.http.GET

interface TVGenreService {
    @GET("genre/tv/list?apiKey=${BuildConfig.API_KEY}")
    suspend fun tvGenres(): TVGenresApi
}
