package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.m0skit0.android.dabestmoviedbapp.di.getTVShowDetailsViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.process

@ExperimentalCoilApi
@Composable
fun TVShowDetailItem(
    tvShowId: Long,
    tvShowDetailsViewModel: TVShowDetailsViewModel = getTVShowDetailsViewModel(tvShowId),
) {
    val viewState = remember { tvShowDetailsViewModel.viewState }
    viewState.value.process<TVShowDetailsView> { details ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TVShowDetailPosterImage(posterUrl = details.poster)
            TVShowDetailText(tvShowDetail = details)
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun TVShowDetailPosterImage(posterUrl: String) {
    Image(
        painter = rememberImagePainter(data = posterUrl),
        contentDescription = null,
        modifier = Modifier
            .padding(5.dp)
            .height(250.dp)
            .width(200.dp)
            .border(2.dp, Color.Black),
    )
}

@Composable
private fun TVShowDetailText(tvShowDetail: TVShowDetailsView) {
    Text(
        text = tvShowDetail.title,
        textAlign = TextAlign.Center,
        fontSize = 25.sp
    )
    Text(
        text = tvShowDetail.voteAverage,
        fontSize = 15.sp
    )
    Text(
        text = tvShowDetail.overview,
        fontSize = 10.sp
    )
}
