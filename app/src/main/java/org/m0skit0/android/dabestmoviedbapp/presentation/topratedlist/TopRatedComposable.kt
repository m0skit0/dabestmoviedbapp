package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.process

@ExperimentalCoilApi
@Composable
fun TopShowList(
    navigateToDetails: (id: Long) -> Unit,
    topRatedListViewModel: TopRatedListViewModel = getViewModel(),
) {
    val viewState = remember { topRatedListViewModel.viewState }
    viewState.value.process<List<TopRatedTVShowView>> { topRatedTVShows ->
        LazyColumn {
            itemsIndexed(topRatedTVShows) { index, item ->
                topRatedListViewModel.checkAndTriggerNextPageLoading(index)
                TopRatedTVShowsItem(
                    navigateToDetails = navigateToDetails,
                    topRatedTVShowData = item
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun TopRatedTVShowsItem(
    navigateToDetails: (id: Long) -> Unit,
    topRatedTVShowData: TopRatedTVShowView
) {
    TopListItemCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onTap = { navigateToDetails(topRatedTVShowData.id) }
                )
            }
        ) {
            with(topRatedTVShowData) {
                TopListItemPosterImage(posterUrl = poster)
                TopListItemTitleAndVoteAverage(title = title, voteAverage = voteAverage)
            }
        }
    }
}

@Composable
private fun TopListItemCard(content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.padding(5.dp),
        content = content,
    )
}

@ExperimentalCoilApi
@Composable
private fun TopListItemPosterImage(posterUrl: String) {
    Image(
        painter = rememberImagePainter(data = posterUrl),
        contentDescription = null,
        modifier = Modifier
            .padding(5.dp)
            .height(150.dp)
            .width(100.dp)
            .border(2.dp, Color.Black),
    )
}

@ExperimentalCoilApi
@Composable
private fun TopListItemTitleAndVoteAverage(title: String, voteAverage: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Text(
            text = voteAverage,
            fontSize = 15.sp
        )
    }
}
