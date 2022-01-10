package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState

const val PROFILE_NAME_TOP_SHOW_LIST = "PROFILE_NAME_TOP_SHOW_LIST"

@ExperimentalCoilApi
@Composable
fun TopRatedTVShowsItem(topRatedTVShowData: TopRatedTVShowsItem) {
    Row {
        Image(
            painter = rememberImagePainter(data = topRatedTVShowData.poster),
            contentDescription = null
        )
        Column {
            Text(text = topRatedTVShowData.title)
            Text(text = topRatedTVShowData.voteAverage)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun TopShowList(topRatedTVShows: List<TopRatedTVShowsItem>) {
    val topRatedState = remember { koin().get<TopTVShowsUseCase>(NAMED_TOP_TV_SHOWS_USE_CASE) }
    LazyColumn {
        items(topRatedTVShows) { item ->
            TopRatedTVShowsItem(topRatedTVShowData = item)
        }
    }
}
