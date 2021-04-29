package id.expert.capstoneproject.core.utils

import id.expert.capstoneproject.core.data.source.local.entity.MoviesEntity
import id.expert.capstoneproject.core.data.source.remote.response.MoviesResponse
import id.expert.capstoneproject.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<MoviesResponse>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(
                id = it.id,
                backdropPath = it.backdropPath.toString(),
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> {
        return input.map {
            Movies(
                id = it.id,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(input: Movies) = MoviesEntity(
        id = input.id,
        backdropPath = input.backdropPath,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )

    fun mapResponseToEntity(input: MoviesResponse, isFavorite: Boolean? = false) = Movies(
        id = input.id,
        backdropPath = input.backdropPath.toString(),
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = isFavorite ?: false
    )
}