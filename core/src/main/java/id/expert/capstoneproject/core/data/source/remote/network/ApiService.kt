package id.expert.capstoneproject.core.data.source.remote.network

import id.expert.capstoneproject.core.BuildConfig.API_KEY
import id.expert.capstoneproject.core.data.source.remote.response.ListMoviesResponse
import id.expert.capstoneproject.core.utils.Constant.Companion.MOVIES_POPULAR
import id.expert.capstoneproject.core.utils.Constant.Companion.MOVIES_SIMILAR
import id.expert.capstoneproject.core.utils.Constant.Companion.STRING_API_KEY
import id.expert.capstoneproject.core.utils.Constant.Companion.STRING_MOVIES_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(MOVIES_POPULAR)
    suspend fun getMovies(
        @Query(STRING_API_KEY) api: String? = API_KEY
    ): ListMoviesResponse

    @GET(MOVIES_SIMILAR)
    suspend fun getSimilarMovies(
        @Path(STRING_MOVIES_ID) moviesId: String,
        @Query(STRING_API_KEY) api: String? = API_KEY
    ): ListMoviesResponse
}