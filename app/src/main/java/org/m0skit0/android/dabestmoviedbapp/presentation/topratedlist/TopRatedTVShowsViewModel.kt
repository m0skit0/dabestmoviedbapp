package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.TopRatedTVShowsUseCase
import javax.inject.Inject

@HiltViewModel
class TopRatedTVShowsViewModel
@Inject constructor(
    private val topRatedTVShowsUseCase: TopRatedTVShowsUseCase
) : ViewModel() {

    private var currentPage = 1

    private var currentTVShowList: List<TopRatedTVShowsItem> = emptyList()

    suspend fun topRatedShows(): List<TopRatedTVShowsItem> =
        topRatedTVShowsUseCase.topTVShows(currentPage++)
            .map { it.toTopRatedListingItem() }
            .let { nextPage ->
                currentTVShowList = currentTVShowList + nextPage
                currentTVShowList
            }
}
