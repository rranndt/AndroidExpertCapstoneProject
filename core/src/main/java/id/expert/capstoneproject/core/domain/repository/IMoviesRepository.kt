package id.expert.capstoneproject.core.domain.repository

import id.expert.capstoneproject.core.data.Resource
import id.expert.capstoneproject.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getAllMovies(): Flow<Resource<List<Movies>>>

    fun getSimilarMovies(moviesId: String): Flow<Resource<List<Movies>>>

    fun getFavoriteMovies(): Flow<List<Movies>>

    fun setFavoriteMovies(movies: Movies, state: Boolean)
}