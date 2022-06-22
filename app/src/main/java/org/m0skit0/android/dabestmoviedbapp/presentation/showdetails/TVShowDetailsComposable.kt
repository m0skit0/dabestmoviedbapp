package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.m0skit0.android.dabestmoviedbapp.presentation.Loading
import org.m0skit0.android.dabestmoviedbapp.presentation.Orientation
import org.m0skit0.android.dabestmoviedbapp.presentation.ViewState
import org.m0skit0.android.dabestmoviedbapp.presentation.process

@ExperimentalCoilApi
@Composable
fun TVShowDetailItem(
    tvShowId: Long,
    tvShowDetailsViewModel: TVShowDetailsViewModel,
) {
    val viewState = remember { mutableStateOf<ViewState>(Loading) }
    LaunchedEffect(key1 = "key") {
        viewState.value = tvShowDetailsViewModel.load(tvShowId)
    }
    viewState.value.process<TVShowDetailsView> { details ->
        Orientation(
            onPortrait = { TVShowDetailItemPortrait(details) },
            onLandscape = { TVShowDetailItemLandscape(details) },
        )
    }
}

@ExperimentalCoilApi
@Composable
private fun TVShowDetailItemPortrait(
    details: TVShowDetailsView,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TVShowDetailPosterImage(posterUrl = details.poster)
        TVShowTitleAndVotingAverage(tvShowDetail = details)
        TVShowDescriptionText(tvShowDetail = details)
    }
}

@ExperimentalCoilApi
@Composable
private fun TVShowDetailItemLandscape(
    details: TVShowDetailsView,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        TVShowDetailPosterImage(posterUrl = details.poster)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TVShowTitleAndVotingAverage(tvShowDetail = details)
            TVShowDescriptionText(tvShowDetail = details)
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun TVShowDetailPosterImage(posterUrl: String) {
    Image(
        painter = rememberImagePainter(data = posterUrl),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .padding(20.dp)
            .height(300.dp)
            .width(200.dp)
            .border(2.dp, Color.Black),
    )
}

@Composable
private fun TVShowTitleAndVotingAverage(tvShowDetail: TVShowDetailsView) {
    Text(
        text = tvShowDetail.title,
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        modifier = Modifier.padding(5.dp)
    )
    Text(
        text = "${tvShowDetail.voteAverage} ${tvShowDetail.voteCount}",
        fontSize = 15.sp,
        modifier = Modifier.padding(5.dp)
    )
    Text(
        text = tvShowDetail.genres,
        textAlign = TextAlign.Center,
        fontSize = 15.sp,
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
private fun TVShowDescriptionText(tvShowDetail: TVShowDetailsView) {
    val scrollState = rememberScrollState(0)
    Text(
        text = tvShowDetail.overview,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(scrollState),
        textAlign = TextAlign.Justify
    )
}
