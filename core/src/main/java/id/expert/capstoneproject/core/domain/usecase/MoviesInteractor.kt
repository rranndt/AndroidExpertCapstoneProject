package id.expert.capstoneproject.core.domain.usecase

import id.expert.capstoneproject.core.data.Resource
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val moviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movies>>> {
        return moviesRepository.getAllMovies()
    }

    override fun getSimilarMovies(moviesId: String): Flow<Resource<List<Movies>>> {
        return moviesRepository.getSimilarMovies(moviesId)
    }

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return moviesRepository.getFavoriteMovies()
    }

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        return moviesRepository.setFavoriteMovies(movies, state)
    }
}