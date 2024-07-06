package com.elsoudany.movieapp.data.remote

import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.models.ResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getAllMovies(): Response<ResponseDto>


}