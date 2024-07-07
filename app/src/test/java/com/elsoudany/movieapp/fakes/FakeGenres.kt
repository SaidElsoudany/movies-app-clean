package com.elsoudany.movieapp.fakes

import com.elsoudany.movieapp.data.local.entity.GenreEntity

object FakeGenres {
    fun getGenres() : List<GenreEntity>{
        return listOf(
            GenreEntity(1, "Action"),
            GenreEntity(2, "Comedy"),
            GenreEntity(3, "Horror"),
            GenreEntity(4, "Sci-Fi"),
            GenreEntity(5, "Drama"),
            GenreEntity(6, "Thriller"),

        )
    }
}