package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentPagerTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_SIMILAR_TV_SHOWS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.ZoomOutPageTransformer
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.errorToast

@AndroidEntryPoint
class TVShowDetailsPagerFragment :
    Fragment(),
    ErrorFragment by koin().get(),
    FetchFragment<List<Long>> by koin().get()
{

    private val similarTVShowsUseCase: SimilarTVShowsUseCase by inject(NAMED_SIMILAR_TV_SHOWS_USE_CASE)

    private lateinit var binding: FragmentPagerTvShowDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pager_tv_show_details, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPagerTvShowDetailsBinding.bind(view)
        lifecycleScope.launch {
            showId()?.let { fetchSimilarShows(it) } ?: errorToast()
        }
    }

    private suspend fun fetchSimilarShows(id: Long) {
        fetch({ similarTVShowsUseCase(id) }) {
            initializePager(it)
        }
    }

    private fun initializePager(list: List<Long>) {
        with(binding.pager) {
            adapter = TVShowDetailsPagerAdapter(
                list,
                childFragmentManager,
                lifecycle
            )
            setPageTransformer(ZoomOutPageTransformer())
        }
    }

    private fun showId(): Long? = arguments?.getLong(KEY_ID)

    companion object {
        private const val KEY_ID = "id"
        fun bundle(id: Long) = bundleOf(KEY_ID to id)
    }
}
