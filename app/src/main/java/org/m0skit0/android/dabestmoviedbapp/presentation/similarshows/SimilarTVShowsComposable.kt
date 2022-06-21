package org.m0skit0.android.dabestmoviedbapp.presentation.similarshows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.m0skit0.android.dabestmoviedbapp.di.getTVShowDetailItemComposable
import org.m0skit0.android.dabestmoviedbapp.presentation.animatePageTransition
import org.m0skit0.android.dabestmoviedbapp.presentation.process

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun SimilarTVShowsPager(
    similarTVShowsViewModel: SimilarTVShowsViewModel,
) {
    val viewState = remember { similarTVShowsViewModel.viewState }
    viewState.value.process<List<Long>> { similarShowsIds ->
        HorizontalPager(count = similarShowsIds.size) { page ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .animatePageTransition(this, page),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, Color.Black),
            ) {
                getTVShowDetailItemComposable(similarShowsIds[page])()
            }
        }
    }
}
