package com.elsoudany.movieapp.models

data class ResponseDto (
    val page: Int,
    val results: List<MovieDto>
)