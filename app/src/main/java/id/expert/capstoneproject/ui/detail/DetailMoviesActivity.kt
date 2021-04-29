package id.expert.capstoneproject.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.expert.capstoneproject.R
import id.expert.capstoneproject.core.data.Resource
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.ui.MoviesSimilarAdapter
import id.expert.capstoneproject.core.utils.Constant.Companion.DATE_CURRENT_FORMAT
import id.expert.capstoneproject.core.utils.Constant.Companion.DATE_REQUIRED_FORMAT
import id.expert.capstoneproject.core.utils.Constant.Companion.EXTRA_MOVIES
import id.expert.capstoneproject.core.utils.Helper.changeDateFormat
import id.expert.capstoneproject.core.utils.gone
import id.expert.capstoneproject.databinding.ActivityDetailMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMoviesBinding
    private val detailMoviesViewModel: DetailMoviesViewModel by viewModel()

    private var newState: Boolean = false
    private var detailMovies: Movies? = null

    private var moviesTitle: String = ""
    private var moviesRating: String = ""
    private var moviesReleaseYear: String = ""
    private var moviesOverview: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailMovies = intent.getParcelableExtra(EXTRA_MOVIES)
        detailMovies?.let { detailMoviesViewModel.setMoviesId(it) }
        binding.result = detailMovies

        showSimilarMovies()
        showDetailMovies(detailMovies)
    }

    private fun showSimilarMovies() {
        val moviesAdapter = MoviesSimilarAdapter()
        moviesAdapter.onItemClick = { selectedData ->
            val intentToDetail = Intent(this, DetailMoviesActivity::class.java)
            intentToDetail.putExtra(EXTRA_MOVIES, selectedData)
            startActivity(intentToDetail)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        binding.rvSimilarMovies.layoutManager =
            LinearLayoutManager(this@DetailMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimilarMovies.setHasFixedSize(true)
        binding.rvSimilarMovies.adapter = moviesAdapter

        detailMoviesViewModel.getSimilarMovies().observe(this, { movieSimilar ->
            when (movieSimilar) {
                is Resource.Loading -> showShimmer()
                is Resource.Success -> {
                    hideShimmer()
                    val data = movieSimilar.data
                    if (data.isNullOrEmpty()) {
                        binding.tvTitleSimilar.gone()
                        binding.similarLine.gone()
                    } else {
                        moviesAdapter.setData(movieSimilar.data)
                    }
                }
                is Resource.Error -> {
                    showShimmer()
                    Toast.makeText(this, getString(R.string.connection_issue), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun showDetailMovies(detailMovies: Movies?) {
        detailMovies?.let {
            supportActionBar?.title = null
            binding.btnShare.setOnClickListener {
                shareMovies()
            }

            moviesTitle = detailMovies.title
            moviesRating = detailMovies.voteAverage.toString()
            moviesReleaseYear = changeDateFormat(
                DATE_CURRENT_FORMAT,
                DATE_REQUIRED_FORMAT,
                detailMovies.releaseDate
            )
            moviesOverview = detailMovies.overview

            var statusFavorite = detailMovies.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMoviesViewModel.setFavoriteItem(detailMovies, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (newState) {
                    showSnackBar(getString(R.string.add_to_favorite, detailMovies.title))
                } else {
                    showSnackBar(getString(R.string.remove_from_favorite, detailMovies.title))
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.fabFavorite.setImageDrawable(
            if (statusFavorite) {
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_solid
                )
            } else {
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            }
        )
        newState = statusFavorite
    }

    private fun shareMovies() {
        val shareMovies = """
            ${getString(R.string.movies_title, moviesTitle)}
            ${getString(R.string.movies_rating, moviesRating)}
            ${getString(R.string.movies_year, moviesReleaseYear)}
            ${getString(R.string.movies_storyline, moviesOverview)}
        """.trimIndent()

        val type = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(type)
            .setText(shareMovies)
            .startChooser()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.contentDetailMain,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showShimmer() {
        binding.rvSimilarMovies.showShimmer()
    }

    private fun hideShimmer() {
        binding.rvSimilarMovies.hideShimmer()
    }

    // Fix MemoryLeak/LibraryLeak di pengguna Android Q
    override fun onBackPressed() {
        finishAfterTransition()
    }
}