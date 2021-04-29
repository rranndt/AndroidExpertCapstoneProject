package id.expert.capstoneproject.core.data.source

import id.expert.capstoneproject.core.data.Resource
import id.expert.capstoneproject.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class RemoteResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                val data = callResult(apiResponse.data)
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Empty -> {
                val data = emptyResult()
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected abstract fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun callResult(data: RequestType): Flow<ResultType>

    protected abstract fun emptyResult(): Flow<ResultType>

    fun asFlow(): Flow<Resource<ResultType>> = result
}