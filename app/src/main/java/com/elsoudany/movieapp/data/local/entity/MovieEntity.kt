package com.elsoudany.movieapp.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("movie_table")
data class MovieEntity(
    @PrimaryKey
    val id : Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val genresIds: String,
    val rating: Float,
    val overview: String,
    var page : Int
) : Parcelable
