package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.Error
import org.m0skit0.android.dabestmoviedbapp.presentation.Loading
import org.m0skit0.android.dabestmoviedbapp.presentation.Progress
import org.m0skit0.android.dabestmoviedbapp.presentation.Result

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
fun TopShowList(
    topRatedListViewModel: TopRatedListViewModel = getViewModel()
) {
    val viewState = remember { topRatedListViewModel.viewState }
    when (viewState.value) {
        is Loading -> Progress()
        is Error -> Error()
        is Result<*> -> (viewState.value as? Result<List<TopRatedTVShowsItem>>)?.data?.let { items ->
            LazyColumn {
                items(items) { item ->
                    TopRatedTVShowsItem(topRatedTVShowData = item)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun TopRatedTVShowsItemPreview(
    topRatedTVShowData: TopRatedTVShowsItem = TopRatedTVShowsItem(
        id = 0L,
        poster = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png",
        title = "Google",
        voteAverage = "10.0"
    )
) {
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
