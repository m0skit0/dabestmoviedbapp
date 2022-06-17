package org.m0skit0.android.dabestmoviedbapp.presentation.similarshows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.m0skit0.android.dabestmoviedbapp.di.getSimilarTVShowsViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.process
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailItem

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun SimilarTVShowsPager(
    tvShowId: Long,
    similarTVShowsViewModel: SimilarTVShowsViewModel = getSimilarTVShowsViewModel(tvShowId)
) {
    val viewState = remember { similarTVShowsViewModel.viewState }
    viewState.value.process<List<Long>> { similarShowsIds ->
        HorizontalPager(count = similarShowsIds.size) { page ->
            TVShowDetailItem(tvShowId = similarShowsIds[page])
        }
    }
}
