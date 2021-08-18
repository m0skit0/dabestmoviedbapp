package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTopRatedTvShowsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailsFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.hasReachedBottom
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.invisible
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.toast
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.visible

@AndroidEntryPoint
class TopRatedTVShowsFragment : Fragment(), OnTVShowClicked {

    private val viewModel: TopRatedTVShowsViewModel by viewModels()

    private lateinit var binding: FragmentTopRatedTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_top_rated_tv_shows, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopRatedTvShowsBinding.bind(view).apply {
            lifecycleOwner = this@TopRatedTVShowsFragment
            topRatedRecycler.setupScrollListenerForNextPage()
        }
        setupErrorListener()
        refresh()
    }

    private fun RecyclerView.setupScrollListenerForNextPage() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (hasReachedBottom()) {
                    refresh()
                }
            }
        })
    }

    private fun setupErrorListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { isError ->
                if (isError) toast(R.string.error_happened)
            }
        }
    }

    private fun refresh() {
        loading()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topRatedShows().let {
                binding.topRatedRecycler updateWith it
            }
            loaded()
        }
    }

    private fun loading() {
        with(binding) {
            loading.visible()
        }
    }

    private infix fun RecyclerView.updateWith(list: List<TopRatedTVShowsItem>) {
        (adapter as? TopRatedListAdapter)?.updateWith(list)
            ?: run {
                adapter = TopRatedListAdapter(list, this@TopRatedTVShowsFragment)
            }
    }

    private fun loaded() {
        with(binding) {
            loading.invisible()
        }
    }

    override fun onClicked(tvShow: TopRatedTVShowsItem) {
        findNavController().navigate(
            R.id.tvShowDetailsFragment,
            TVShowDetailsFragment.bundle(tvShow.id)
        )
    }
}
