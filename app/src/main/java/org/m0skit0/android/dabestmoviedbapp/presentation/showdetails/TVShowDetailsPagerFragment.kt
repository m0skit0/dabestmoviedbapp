package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.FragmentPagerTvShowDetailsBinding

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
        FragmentPagerTvShowDetailsBinding.bind(view).apply {
            pager.adapter = ShowDetailsPagerAdapter(
                viewModel,
                childFragmentManager,
                lifecycle
            )
        }
    }
}
