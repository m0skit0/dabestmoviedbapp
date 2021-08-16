package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.m0skit0.android.dabestmoviedbapp.databinding.ItemTopRatedTvShowBinding

class TopRatedListAdapter(
    private val topRatedShows: List<TopRatedTVShowsItem>
) : RecyclerView.Adapter<TopRatedListAdapter.TopRatedTVShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedTVShowViewHolder =
        LayoutInflater.from(parent.context).let { layoutInflater ->
            ItemTopRatedTvShowBinding.inflate(layoutInflater, parent, false).let { binding ->
                TopRatedTVShowViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: TopRatedTVShowViewHolder, position: Int) {
        topRatedShows[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = topRatedShows.size

    inner class TopRatedTVShowViewHolder(
        private val binding: ItemTopRatedTvShowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowItem: TopRatedTVShowsItem) {
            binding.run {
                tvShowData = tvShowItem
                executePendingBindings()
            }
        }
    }
}
