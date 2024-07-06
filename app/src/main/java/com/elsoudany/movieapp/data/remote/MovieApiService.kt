package com.elsoudany.movieapp.data.remote

import com.elsoudany.movieapp.models.GenresResponseDto
import com.elsoudany.movieapp.models.MoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/movie/popular")
    suspend fun downloadMovies(@Query("page") page: Int): Response<MoviesResponseDto>

    @GET("3/genre/movie/list")
    suspend fun getGenres(): Response<GenresResponseDto>
}