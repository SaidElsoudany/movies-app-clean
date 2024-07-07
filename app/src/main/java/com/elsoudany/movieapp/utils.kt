package com.elsoudany.movieapp

import com.elsoudany.movieapp.data.local.entity.GenreEntity
import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.models.GenreDto
import com.elsoudany.movieapp.models.MovieDto

fun List<GenreDto>.toGenreEntityList(): List<GenreEntity>{
    return this.map {
        GenreEntity(
            it.id,
            it.name
        )
    }
}

fun List<MovieDto>.toMovieEntityList(): List<MovieEntity>{
    return this.map {
        MovieEntity(
            it.id,
            it.title,
            it.posterPath,
            it.releaseDate,
            it.genresIds.joinToString(),
            it.rating,
            it.overview,
            it.page
        )
    }
}