package id.expert.capstoneproject.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.expert.capstoneproject.core.domain.usecase.MoviesUseCase

class MoviesViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {

    val movies = moviesUseCase.getAllMovies().asLiveData()
}