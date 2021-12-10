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
import org.m0skit0.android.dabestmoviedbapp.data.toOriginalPosterFullUrl
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*

class TVShowDetailsFragment :
    Fragment(),
    FetchFragment<TVShowDetailsData> by koin().get(),
    ErrorFragment by koin().get()
{

    private val tvShowDetails: TVShowDetailsUseCase by inject()

    private lateinit var binding: FragmentTvShowDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_tv_show_details, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTvShowDetailsBinding.bind(view).apply {
            lifecycleOwner = this@TVShowDetailsFragment
            setLoadingView(loading)
        }
        lifecycleScope.launch { fetchShowDetails() }
    }

    private suspend fun fetchShowDetails() {
        showId()?.let { id ->
            fetch({ tvShowDetails(id) }) { details ->
                details.isEmpty().not().also { isNotEmpty ->
                    if (isNotEmpty) {
                        with(binding) {
                            tvShowDetails = details.toTVShowDetailsPresentation()
                            details.loadPoster(requireActivity())
                        }
                    }
                }
            }
        }
    }

    private fun TVShowDetailsData.loadPoster(context: Context) {
        Glide.with(context)
            .load(posterPath.toOriginalPosterFullUrl())
            .error(R.drawable.image_error)
            .into(binding.poster)
    }

    private fun showId(): Long? = arguments?.getLong(KEY_ID)

    private fun TVShowDetailsData.isEmpty() = id == -1L

    companion object {
        private const val KEY_ID = "id"
        fun bundle(id: Long) = bundleOf(KEY_ID to id)
    }
}
