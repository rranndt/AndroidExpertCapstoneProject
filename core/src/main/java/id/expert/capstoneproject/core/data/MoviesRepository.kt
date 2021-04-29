package id.expert.capstoneproject.core.data

import id.expert.capstoneproject.core.data.source.RemoteResource
import id.expert.capstoneproject.core.data.source.local.LocalDataSource
import id.expert.capstoneproject.core.data.source.remote.RemoteDataSource
import id.expert.capstoneproject.core.data.source.remote.network.ApiResponse
import id.expert.capstoneproject.core.data.source.remote.response.MoviesResponse
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.domain.repository.IMoviesRepository
import id.expert.capstoneproject.core.utils.AppExecutors
import id.expert.capstoneproject.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {
            override fun loadFromDb(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(moviesList)
            }
        }.asFlow()

    override fun getSimilarMovies(moviesId: String): Flow<Resource<List<Movies>>> =
        object : RemoteResource<List<Movies>, List<MoviesResponse>>() {
            override fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> {
                return remoteDataSource.getSimilarMovies(moviesId)
            }

            override fun callResult(data: List<MoviesResponse>): Flow<List<Movies>> {
                val result = data.map {
                    DataMapper.mapResponseToEntity(it)
                }
                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<List<Movies>> {
                return flow { emit(emptyList<Movies>()) }
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(moviesEntity, state) }
    }

}