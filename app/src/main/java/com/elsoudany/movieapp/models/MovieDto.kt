package com.elsoudany.movieapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id : Int,
    val title: String,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String
)
