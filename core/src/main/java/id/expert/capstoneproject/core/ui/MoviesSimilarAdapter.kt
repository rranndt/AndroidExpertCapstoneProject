package id.expert.capstoneproject.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.expert.capstoneproject.core.BuildConfig.IMAGE_URL
import id.expert.capstoneproject.core.R
import id.expert.capstoneproject.core.databinding.RecyclerItemGridSimilarMoviesBinding
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.utils.Helper

class MoviesSimilarAdapter : RecyclerView.Adapter<MoviesSimilarAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesSimilarAdapter.ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_grid_similar_movies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesSimilarAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerItemGridSimilarMoviesBinding.bind(itemView)
        fun bind(data: Movies) {
            with(binding) {
                Helper.setGlide(
                    itemView.context,
                    "$IMAGE_URL${data.posterPath}",
                    ivPoster
                )
                tvTitle.text = data.title
                ratingBar.rating = data.voteAverage / 2
                tvRating.text = data.voteAverage.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}