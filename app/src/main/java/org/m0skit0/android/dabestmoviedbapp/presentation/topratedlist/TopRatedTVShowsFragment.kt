package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTopRatedTvShowsBinding
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_FETCH_FRAGMENT_DEFAULT
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailsPagerFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*

class TopRatedTVShowsFragment :
    Fragment(),
    OnTVShowClicked,
    FetchFragment<List<TopRatedTVShowData>> by koin().get(NAMED_FETCH_FRAGMENT_DEFAULT),
    KoinComponent {

    private val topRatedTVShowsUseCase: TopTVShowsUseCase by inject(NAMED_TOP_TV_SHOWS_USE_CASE)

    private var topRatedTVShowsAdapter: TopRatedListAdapter? = null

    private lateinit var binding: FragmentTopRatedTvShowsBinding

    private var currentPage = 1

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
        loadTopTVShowsState()
    }

    private fun RecyclerView.setupScrollListenerForNextPage() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (hasReachedBottom()) nextPage()
            }
        })
    }

    private fun loadTopTVShowsState() {
        topRatedTVShowsAdapter?.run {
            setAdapterToRecyclerView()
            loaded()
        } ?: run {
            nextPage()
        }
    }

    private fun nextPage() {
        fetch({ topRatedTVShowsUseCase(currentPage) }) { tvShowsData ->
            tvShowsData.map { it.toTopRatedListingItem() }.let { newPage ->
                topRatedTVShowsAdapter?.updateWith(newPage) ?: run {
                    topRatedTVShowsAdapter = TopRatedListAdapter(newPage, this)
                    setAdapterToRecyclerView()
                }
            }
            currentPage++
        }
    }

    private fun setAdapterToRecyclerView() {
        binding.topRatedRecycler.adapter = topRatedTVShowsAdapter
    }

    override fun onClicked(tvShow: TopRatedTVShowsItem) {
        findNavController().navigate(
            R.id.tvShowDetailsPagerFragment,
            TVShowDetailsPagerFragment.bundle(tvShow.id)
        )
    }
}
