package id.expert.capstoneproject.core.data.source.remote

import id.expert.capstoneproject.core.data.source.remote.network.ApiResponse
import id.expert.capstoneproject.core.data.source.remote.network.ApiService
import id.expert.capstoneproject.core.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.moviesResponses

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.moviesResponses))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getSimilarMovies(moviesId: String): Flow<ApiResponse<List<MoviesResponse>>> {
        return flow {
            try {
                val response = apiService.getSimilarMovies(moviesId = moviesId)
                val dataArray = response.moviesResponses

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.moviesResponses))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}