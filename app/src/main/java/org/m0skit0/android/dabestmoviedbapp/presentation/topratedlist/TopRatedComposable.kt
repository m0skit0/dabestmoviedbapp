package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData

@ExperimentalCoilApi
@Composable
fun TopShowItem(topRatedTVShowData: TopRatedTVShowData) {
    Row {
        Image(
            painter = rememberImagePainter(data = topRatedTVShowData.imagePath),
            contentDescription = null
        )
        Column {
            Text(text = topRatedTVShowData.name)
            Text(text = topRatedTVShowData.voteAverage.toString())
        }
    }

}
