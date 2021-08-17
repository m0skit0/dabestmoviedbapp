package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.m0skit0.android.dabestmoviedbapp.R
import org.m0skit0.android.dabestmoviedbapp.databinding.ItemTopRatedTvShowBinding

class TopRatedListAdapter(
    var topRatedShows: List<TopRatedTVShowsItem> = emptyList()
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

    infix fun updateWith(list: List<TopRatedTVShowsItem>) {
        val positionStartForNewList = topRatedShows.size
        topRatedShows = list
        notifyItemRangeInserted(positionStartForNewList, list.size)
    }

    inner class TopRatedTVShowViewHolder(
        private val binding: ItemTopRatedTvShowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShowItem: TopRatedTVShowsItem) {
            binding.run {
                tvShowData = tvShowItem
                executePendingBindings()
                tvShowItem.loadPoster(root.context, binding)
            }
        }

        private fun TopRatedTVShowsItem.loadPoster(context: Context, binding: ItemTopRatedTvShowBinding) {
            Glide.with(context)
                .load(poster)
                .thumbnail(0.5f)
                .error(R.drawable.image_error)
                .into(binding.poster)
        }
    }
}
