package id.expert.capstoneproject.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("id")
    val id: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int
)