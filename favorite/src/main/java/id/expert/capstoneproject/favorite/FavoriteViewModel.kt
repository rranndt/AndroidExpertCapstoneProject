package id.expert.capstoneproject.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.expert.capstoneproject.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {

    val favoriteMovies = moviesUseCase.getFavoriteMovies().asLiveData()
}