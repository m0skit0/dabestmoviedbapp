package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import arrow.core.Either
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTopRatedTvShowsBinding
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_RATED_TV_SHOWS_USECASE
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailsPagerFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*

class TopRatedTVShowsFragmentFunctional :
    Fragment(),
    OnTVShowClicked,
    CollectFragment<List<TopRatedTVShowsItem>> by CollectFragmentImpl(),
    KoinComponent {

    private val topRatedTVShowsUseCase: suspend (Int) -> Either<Throwable, List<TopRatedTVShowsItem>> by inject(NAMED_TOP_RATED_TV_SHOWS_USECASE)

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
            lifecycleOwner = this@TopRatedTVShowsFragmentFunctional
            topRatedRecycler.setupScrollListenerForNextPage()
            setLoadingView(loading)
        }
        nextPage()
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
        lifecycleScope.launch {
            topRatedTVShowsUseCase(currentPage).fold({
                Log.e("ERROR", "Error", it)
                errorToast()
            }) {
                currentPage++
                binding.topRatedRecycler updateWith it
            }
        }
    }

    private infix fun RecyclerView.updateWith(list: List<TopRatedTVShowsItem>) {
        (adapter as? TopRatedListAdapter)?.updateWith(list)
            ?: run {
                adapter = TopRatedListAdapter(list, this@TopRatedTVShowsFragmentFunctional)
            }
    }

    override fun onClicked(tvShow: TopRatedTVShowsItem) {
        findNavController().navigate(
            R.id.tvShowDetailsPagerFragment,
            TVShowDetailsPagerFragment.bundle(tvShow.id)
        )
    }
}
