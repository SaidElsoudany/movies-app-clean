package com.elsoudany.movieapp.data.repository

import com.elsoudany.movieapp.data.remote.MovieApiService
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.models.ResponseDto
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieApiService
): MoviesRepository {
    override suspend fun getAllMovies(): Response<ResponseDto> {
        return remoteDataSource.getAllMovies()
    }
}