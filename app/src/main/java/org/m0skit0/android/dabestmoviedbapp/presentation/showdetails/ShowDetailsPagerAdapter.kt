package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShowDetailsPagerAdapter(
    private val viewModel: TVShowDetailsViewModel,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = viewModel.similarShowsSize

    override fun createFragment(position: Int): Fragment =
        TVShowDetailsFragment().apply {
            arguments = TVShowDetailsFragment.bundle(viewModel.nextSimilarShow(position))
        }
}
