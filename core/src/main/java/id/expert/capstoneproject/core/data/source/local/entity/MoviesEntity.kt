package id.expert.capstoneproject.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.expert.capstoneproject.core.utils.Constant.Companion.TABLE_NAME_MOVIES

@Entity(tableName = TABLE_NAME_MOVIES)
data class MoviesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Float,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Float,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)