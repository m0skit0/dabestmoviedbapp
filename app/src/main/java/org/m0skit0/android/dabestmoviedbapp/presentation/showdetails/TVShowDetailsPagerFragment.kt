package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentPagerTvShowDetailsBinding
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.toast

@AndroidEntryPoint
class TVShowDetailsPagerFragment : Fragment() {

    private val viewModel: TVShowDetailsViewModel by activityViewModels()

    private lateinit var binding: FragmentPagerTvShowDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pager_tv_show_details, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            arguments?.getLong(KEY_ID)?.let {
                viewModel.setInitialId(it)
                FragmentPagerTvShowDetailsBinding.bind(view).apply {
                    initializePager()
                }
            } ?: toast(R.string.error_happened)
        }
    }

    private fun FragmentPagerTvShowDetailsBinding.initializePager() {
        pager.adapter = TVShowDetailsPagerAdapter(
            viewModel,
            childFragmentManager,
            lifecycle
        )
    }

    companion object {
        private const val KEY_ID = "id"
        fun bundle(id: Long) = bundleOf(KEY_ID to id)
    }
}
