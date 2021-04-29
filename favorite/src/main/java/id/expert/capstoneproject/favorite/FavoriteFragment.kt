package id.expert.capstoneproject.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.expert.capstoneproject.R
import id.expert.capstoneproject.core.ui.MoviesAdapter
import id.expert.capstoneproject.core.utils.Constant.Companion.EXTRA_MOVIES
import id.expert.capstoneproject.favorite.databinding.FragmentFavoriteBinding
import id.expert.capstoneproject.favorite.di.favoriteModule
import id.expert.capstoneproject.ui.detail.DetailMoviesActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            loadKoinModules(favoriteModule)

            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = { selectedData ->
                val intentToDetail = Intent(activity, DetailMoviesActivity::class.java)
                intentToDetail.putExtra(EXTRA_MOVIES, selectedData)
                startActivity(intentToDetail)
                activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, { dataMovies ->
                moviesAdapter.setData(dataMovies)
                binding?.viewNoData?.root?.visibility =
                    if (dataMovies.isNotEmpty()) View.GONE else View.VISIBLE
            })

            binding?.rvFavoriteMovies?.layoutManager = GridLayoutManager(context, 2)
            binding?.rvFavoriteMovies?.setHasFixedSize(true)
            binding?.rvFavoriteMovies?.adapter = moviesAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}