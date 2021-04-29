package id.expert.capstoneproject.ui.detail

import androidx.lifecycle.*
import id.expert.capstoneproject.core.data.Resource
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.domain.usecase.MoviesUseCase

class DetailMoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    private var moviesList = MutableLiveData<Movies>()

    private var similarMovies = moviesList.switchMap {
        moviesUseCase.getSimilarMovies(it.id).asLiveData()
    }

    fun setMoviesId(moviesId: Movies) {
        moviesList.postValue(moviesId)
    }

    fun getSimilarMovies(): LiveData<Resource<List<Movies>>> = similarMovies

    fun setFavoriteItem(movies: Movies, newStatus: Boolean) =
        moviesUseCase.setFavoriteMovies(movies, newStatus)

}