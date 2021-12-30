package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_FETCH_FRAGMENT_DEFAULT
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TV_SHOW_DETAILS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.FetchFragment
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState

class TVShowDetailsFragment :
    Fragment(),
    FetchFragment<ShowDetailsState> by koin().get(NAMED_FETCH_FRAGMENT_DEFAULT)
{

    private val tvShowDetails: TVShowDetailsUseCase by inject(NAMED_TV_SHOW_DETAILS_USE_CASE)

    private lateinit var binding: FragmentTvShowDetailsBinding

    private val state: ShowDetailsState
        get() = arguments?.getSerializable(KEY_STATE) as? ShowDetailsState ?: ShowDetailsState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_tv_show_details, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTvShowDetailsBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            setLoadingView(loading)
        }
        lifecycleScope.launch { fetchShowDetails(state) }
    }

    private suspend fun fetchShowDetails(state: ShowDetailsState) {
        fetch({ tvShowDetails(state) }) { newState ->
            newState.tvShowDetails?.run {
                if (!isEmpty()) {
                    binding.tvShowDetails = toTVShowDetailsPresentation()
                    loadPoster(requireActivity())
                }
            }
        }
    }

    private fun TVShowDetailsData.loadPoster(context: Context) {
        Glide.with(context)
            .load(posterPath)
            .error(R.drawable.image_error)
            .into(binding.poster)
    }

    private fun TVShowDetailsData.isEmpty() = id == -1L

    companion object {
        private const val KEY_STATE = "state"
        fun bundle(state: ShowDetailsState) = bundleOf(KEY_STATE to state)
    }
}
