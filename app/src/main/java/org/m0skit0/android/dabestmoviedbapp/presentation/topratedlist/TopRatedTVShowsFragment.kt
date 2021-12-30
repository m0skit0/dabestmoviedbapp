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
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTopRatedTvShowsBinding
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_FETCH_FRAGMENT_DEFAULT
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailsPagerFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState
import org.m0skit0.android.dabestmoviedbapp.state.updateCurrentShowIdWith
import org.m0skit0.android.dabestmoviedbapp.state.updateWithNextPage

class TopRatedTVShowsFragment :
    Fragment(),
    OnTVShowClicked,
    FetchFragment<ApplicationState> by koin().get(NAMED_FETCH_FRAGMENT_DEFAULT),
    KoinComponent {

    private val topRatedTVShowsUseCase: TopTVShowsUseCase by inject(NAMED_TOP_TV_SHOWS_USE_CASE)

    private lateinit var binding: FragmentTopRatedTvShowsBinding

    private var state = ApplicationState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_top_rated_tv_shows, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopRatedTvShowsBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            topRatedRecycler.setupScrollListenerForNextPage()
            setLoadingView(loading)
        }
        binding.topRatedRecycler.adapter ?: nextPage()
    }

    private fun RecyclerView.setupScrollListenerForNextPage() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (hasReachedBottom()) nextPage()
            }
        })
    }

    private fun nextPage() {
        fetch({ topRatedTVShowsUseCase(state) }) { newState ->
            newState.topRatedState.topRatedShows.map { it.toTopRatedListingItem() }.setIntoAdapter()
            state = newState.updateWithNextPage()
        }
    }

    private fun List<TopRatedTVShowsItem>.setIntoAdapter() {
        (binding.topRatedRecycler.adapter as? TopRatedListAdapter)?.updateWith(this)
            ?: TopRatedListAdapter(this, this@TopRatedTVShowsFragment).let {
                binding.topRatedRecycler.adapter = it
            }
    }

    override fun onClicked(tvShow: TopRatedTVShowsItem) {
        findNavController().navigate(
            R.id.tvShowDetailsPagerFragment,
            TVShowDetailsPagerFragment.bundle(state updateCurrentShowIdWith tvShow.id)
        )
    }
}
