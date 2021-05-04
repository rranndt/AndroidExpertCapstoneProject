package id.expert.capstoneproject.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.expert.capstoneproject.core.databinding.RecyclerItemGridSimilarMoviesBinding
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.utils.DiffUtils

class MoviesSimilarAdapter : RecyclerView.Adapter<MoviesSimilarAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>) {
        val diffCallback = DiffUtils(listData, newListData)
        val diffUtil = DiffUtil.calculateDiff(diffCallback)
        listData.clear()
        listData.addAll(newListData)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesSimilarAdapter.ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemGridSimilarMoviesBinding.inflate(layoutInflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesSimilarAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(private val binding: RecyclerItemGridSimilarMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movies) {
            with(binding) {
                result = data
                executePendingBindings()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}