package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.*

@AndroidEntryPoint
class TVShowDetailsFragment :
    Fragment(),
    CollectFragment<TVShowDetailsPresentation> by CollectFragmentImpl(),
    ErrorFragment by ErrorFragmentImpl()
{

    private val viewModel: TVShowDetailsViewModel by viewModels()

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
        setupErrorListener(this, viewModel)
        collect()
    }

    private fun collect() {
        showId()?.let { id ->
            viewModel.tvShowDetails(id).collect(viewLifecycleOwner) { details ->
                details.isEmpty().not().also { isNotEmpty ->
                    if (isNotEmpty) {
                        with(binding) {
                            tvShowDetails = details
                            details.loadPoster(requireActivity())
                        }
                    }
                }
            }
        } ?: errorToast()
    }

    private fun TVShowDetailsPresentation.loadPoster(context: Context) {
        Glide.with(context)
            .load(poster)
            .error(R.drawable.image_error)
            .into(binding.poster)
    }

    companion object {
        private const val KEY_ID = "id"
        fun bundle(id: Long) = bundleOf(KEY_ID to id)
        private fun TVShowDetailsFragment.showId(): Long? = arguments?.getLong(KEY_ID)
    }
}
