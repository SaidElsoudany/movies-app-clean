package com.elsoudany.movieapp.models

data class MoviesResponseDto (
    val page: Int,
    val results: List<MovieDto>,

)