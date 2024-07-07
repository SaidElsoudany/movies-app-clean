package com.elsoudany.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("genre_table")
data class GenreEntity(
    @PrimaryKey
    val id : Int,
    val name: String
)
