package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.*
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorFragmentImpl
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.RefreshFragment
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.RefreshFragmentImpl

@AndroidEntryPoint
class TVShowDetailsFragment :
    Fragment(),
    RefreshFragment by RefreshFragmentImpl(),
    ErrorFragment by ErrorFragmentImpl()
{

    private val viewModel: TVShowDetailsViewModel by activityViewModels()

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
        refresh()
    }

    private fun refresh() {
        refresh(viewLifecycleOwner) {
            arguments?.getLong(KEY_ID)?.let { id ->
                viewModel.tvShowDetails(id).let {
                    with(binding) {
                        tvShowDetails = it
                        it.loadPoster(this@TVShowDetailsFragment.requireActivity(), )
                    }

                }
            } ?: toast(R.string.error_happened)
        }
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
    }
}
