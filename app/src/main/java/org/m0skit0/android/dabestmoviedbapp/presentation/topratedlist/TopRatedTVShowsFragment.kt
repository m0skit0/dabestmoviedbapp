package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTopRatedTvShowsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailsPagerFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*

@AndroidEntryPoint
class TopRatedTVShowsFragment :
    Fragment(),
    OnTVShowClicked,
    RefreshFragment by RefreshFragmentImpl(),
    ErrorFragment by ErrorFragmentImpl()
{

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
            setLoadingView(loading)
        }
        setupErrorListener(this, viewModel)
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

    private fun refresh() {
        refresh(viewLifecycleOwner) {
            viewModel.topRatedShows().let {
                binding.topRatedRecycler updateWith it
            }
        }
    }

    private infix fun RecyclerView.updateWith(list: List<TopRatedTVShowsItem>) {
        (adapter as? TopRatedListAdapter)?.updateWith(list)
            ?: run {
                adapter = TopRatedListAdapter(list, this@TopRatedTVShowsFragment)
            }
    }

    override fun onClicked(tvShow: TopRatedTVShowsItem) {
        findNavController().navigate(
            R.id.tvShowDetailsPagerFragment,
            TVShowDetailsPagerFragment.bundle(tvShow.id)
        )
    }
}
