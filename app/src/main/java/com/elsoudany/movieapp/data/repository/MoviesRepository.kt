package com.elsoudany.movieapp.data.repository

import com.elsoudany.movieapp.models.GenericResult
import com.elsoudany.movieapp.models.GenreDto
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.models.MoviesResponseDto
import retrofit2.Response

interface MoviesRepository {

    suspend fun downloadMovies(pageNumber: Int): GenericResult<List<MovieDto>>
    suspend fun getGenres(): GenericResult<List<GenreDto>>
}