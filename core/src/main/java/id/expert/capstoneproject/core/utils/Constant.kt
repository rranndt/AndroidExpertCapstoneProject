package id.expert.capstoneproject.core.utils

class Constant {
    companion object {

        // Url
        const val MOVIES_POPULAR = "movie/popular"
        const val MOVIES_SIMILAR = "movie/{movie_id}/similar"

        // Api Service
        const val STRING_API_KEY = "api_key"
        const val STRING_MOVIES_ID = "movie_id"

        // Room
        const val TABLE_NAME_MOVIES = "movies"

        // Bundle
        const val EXTRA_MOVIES = "extra_movies"

        // Date
        const val DATE_CURRENT_FORMAT = "yyyy-MM-dd"
        const val DATE_REQUIRED_FORMAT = "yyyy"
    }
}