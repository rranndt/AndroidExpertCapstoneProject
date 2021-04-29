package id.expert.capstoneproject.core.data.source.local

import id.expert.capstoneproject.core.data.source.local.entity.MoviesEntity
import id.expert.capstoneproject.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val moviesDao: MoviesDao) {

    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MoviesEntity>> = moviesDao.getFavoriteMovies()

    suspend fun insertMovies(moviesList: List<MoviesEntity>) = moviesDao.insertMovies(moviesList)

    fun setFavoriteMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        moviesDao.updateFavoriteMovies(movies)
    }
}