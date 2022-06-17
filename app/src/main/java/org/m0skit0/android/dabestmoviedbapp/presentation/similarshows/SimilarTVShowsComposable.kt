package org.m0skit0.android.dabestmoviedbapp.presentation.similarshows

import androidx.compose.runtime.Composable
import org.m0skit0.android.dabestmoviedbapp.di.getSimilarTVShowsViewModel

@Composable
fun SimilarTVShowsPager(
    tvShowId: Long,
    similarTVShowsViewModel: SimilarTVShowsViewModel = getSimilarTVShowsViewModel(tvShowId)
) {

}
