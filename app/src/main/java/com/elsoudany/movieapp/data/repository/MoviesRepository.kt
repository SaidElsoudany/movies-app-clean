package com.elsoudany.movieapp.data.repository

import com.elsoudany.movieapp.data.remote.MovieApiService
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.models.ResponseDto
import retrofit2.Response

interface MoviesRepository {
    suspend fun getAllMovies(): Response<ResponseDto>

}