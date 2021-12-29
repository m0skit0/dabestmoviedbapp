package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentPagerTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_FETCH_FRAGMENT_NO_LOADING
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_SIMILAR_TV_SHOWS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowsUseCaseState
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.applicationState
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.similarTVShowIds
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.ZoomOutPageTransformer
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragment
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

class TVShowDetailsPagerFragment :
    Fragment(),
    ErrorFragment by koin().get(),
    FetchFragment<SimilarTVShowsUseCaseState> by koin().get(NAMED_FETCH_FRAGMENT_NO_LOADING)
{

    private val similarTVShowsUseCase: SimilarTVShowsUseCase by inject(NAMED_SIMILAR_TV_SHOWS_USE_CASE)

    private lateinit var binding: FragmentPagerTvShowDetailsBinding

    private val state: ApplicationState
        get() = arguments?.getSerializable(KEY_STATE) as? ApplicationState ?: ApplicationState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pager_tv_show_details, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPagerTvShowDetailsBinding.bind(view)
        lifecycleScope.launch {
            fetchSimilarShows(state)
        }
    }

    private suspend fun fetchSimilarShows(state: ApplicationState) {
        fetch({ similarTVShowsUseCase(state) }) { newState ->
            initializePager(newState)
        }
    }

    private fun initializePager(state: SimilarTVShowsUseCaseState) {
        with(binding.pager) {
            adapter = TVShowDetailsPagerAdapter(
                state.applicationState,
                listOf(state.applicationState.showDetailsState.currentShowId) + state.similarTVShowIds,
                childFragmentManager,
                lifecycle
            )
            setPageTransformer(ZoomOutPageTransformer())
        }
    }

    companion object {
        private const val KEY_STATE = "state"
        fun bundle(state: ApplicationState) = bundleOf(KEY_STATE to state)
    }
}
