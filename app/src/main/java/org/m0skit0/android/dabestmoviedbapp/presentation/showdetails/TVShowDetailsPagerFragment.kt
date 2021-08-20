package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentPagerTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.ZoomOutPageTransformer
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.errorToast

@AndroidEntryPoint
class TVShowDetailsPagerFragment :
    Fragment(),
    ErrorFragment by ErrorFragmentImpl(),
    CollectFragment<List<Long>> by CollectFragmentImpl(EmptyLoadingFragment())
{

    private val viewModel: TVShowDetailsPagerViewModel by viewModels()

    private lateinit var binding: FragmentPagerTvShowDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pager_tv_show_details, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPagerTvShowDetailsBinding.bind(view)
        viewLifecycleOwner.lifecycleScope.launch {
            showId()?.let {
                startCollecting(it)
                setupErrorListener(this@TVShowDetailsPagerFragment, viewModel)
            } ?: errorToast()
        }
    }

    private suspend fun startCollecting(id: Long) {
        collect()
        viewModel.loadShows(id)
    }

    private suspend fun collect() {
        viewModel.shows.collect(viewLifecycleOwner) { list ->
            list.isEmpty().not().apply {
                initializePager(list)
            }
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

    companion object {
        private const val KEY_ID = "id"
        fun bundle(id: Long) = bundleOf(KEY_ID to id)
        private fun TVShowDetailsPagerFragment.showId(): Long? = arguments?.getLong(KEY_ID)
    }
}
