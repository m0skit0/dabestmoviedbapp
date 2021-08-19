package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsPagerViewModel @Inject constructor(
    private val similarTVShowUseCase: SimilarTVShowUseCase,
) : ViewModel(), ErrorViewModel by ErrorViewModelImpl() {

    private var shows: List<Long> = emptyList()

    val similarShowsSize: Int
        get() = shows.size

    suspend fun loadShows(id: Long) {
        shows = listOf(id) + similarTVShowUseCase.similarTVShows(id).map { it.id }
    }

    fun getShowInPosition(position: Int): Long = shows.getOrElse(position) { -1 }
}
