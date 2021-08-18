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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.invisible
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.toast
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.visible

@AndroidEntryPoint
class TVShowDetailsFragment : Fragment() {

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
        }
        setupErrorListener()
        refresh()
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
            arguments?.getLong(KEY_ID)?.let { id ->
                viewModel.tvShowDetails(id).let {
                    binding.tvShowDetails = it
                }
            } ?: toast(R.string.error_happened)
            loaded()
        }
    }

    private fun loading() {
        with(binding) {
            loading.visible()
        }
    }

    private fun loaded() {
        with(binding) {
            loading.invisible()
        }
    }

    companion object {
        private const val KEY_ID = "id"
        fun bundle(id: Long) = bundleOf(KEY_ID to id)
    }
}
