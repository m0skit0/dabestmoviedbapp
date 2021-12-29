package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState
import org.m0skit0.android.dabestmoviedbapp.state.updateCurrentShowIdWith

class TVShowDetailsPagerAdapter(
    val state: ApplicationState,
    private val list: List<Long>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment =
        TVShowDetailsFragment().apply {
            arguments = TVShowDetailsFragment.bundle(state updateCurrentShowIdWith list.getOrDefault(position))
        }

    private fun List<Long>.getOrDefault(position: Int) = getOrElse(position) { -1 }
}
