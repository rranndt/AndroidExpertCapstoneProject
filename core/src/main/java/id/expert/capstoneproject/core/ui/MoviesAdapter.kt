package id.expert.capstoneproject.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.expert.capstoneproject.core.databinding.RecyclerItemGridBinding
import id.expert.capstoneproject.core.domain.model.Movies

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {

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
    ): MoviesAdapter.ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemGridBinding.inflate(layoutInflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(private val binding: RecyclerItemGridBinding) :
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