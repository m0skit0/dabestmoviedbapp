package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.*

@ExperimentalCoilApi
@Composable
fun TopRatedTVShowsItem(topRatedTVShowData: TopRatedTVShowsItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = topRatedTVShowData.poster),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
        is Result<*> -> (viewState.value.asResultTopRatedTVShowsItems())?.data?.let { items ->
            LazyColumn {
                items(items) { item ->
                    TopRatedTVShowsItem(topRatedTVShowData = item)
                }
            }
        }
    }
}

private fun <T : ViewState> T.asResultTopRatedTVShowsItems(): Result<List<TopRatedTVShowsItem>>? =
    this as? Result<List<TopRatedTVShowsItem>>

@ExperimentalCoilApi
@Preview
@Composable
fun TopRatedTVShowsItemPreview(
    topRatedTVShowData: TopRatedTVShowsItem = TopRatedTVShowsItem(
        id = 0L,
        poster = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_dark_color_272x92dp.png",
        title = "Google",
        voteAverage = "10.0"
    )
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = topRatedTVShowData.poster),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = topRatedTVShowData.title)
            Text(text = topRatedTVShowData.voteAverage)
        }
    }
}
