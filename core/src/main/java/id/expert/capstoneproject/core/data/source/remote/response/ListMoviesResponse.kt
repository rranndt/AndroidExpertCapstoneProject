package id.expert.capstoneproject.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(
    @SerializedName("results")
    val moviesResponses: List<MoviesResponse>,

    @SerializedName("total_results")
    val total_results: Int

)