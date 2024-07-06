package com.elsoudany.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDto(
    val id : Int,
    val title: String,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String,
    @SerializedName("genre_ids")
    @Expose
    val genresIds: List<Int>,
    @SerializedName("vote_average")
    @Expose
    val rating: Float,
    val overview: String,
    var page : Int
) : Parcelable
