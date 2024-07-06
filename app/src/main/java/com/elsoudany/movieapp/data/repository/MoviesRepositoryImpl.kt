package com.elsoudany.movieapp.data.repository

import com.elsoudany.movieapp.data.remote.MovieApiService
import com.elsoudany.movieapp.models.GenericResult
import com.elsoudany.movieapp.models.GenreDto
import com.elsoudany.movieapp.models.MovieDto
import okhttp3.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieApiService
) : MoviesRepository {
    override suspend fun downloadMovies(pageNumber: Int): GenericResult<List<MovieDto>> {
        return try {
            val moviesResponse = remoteDataSource.downloadMovies(pageNumber)
            if (moviesResponse.isSuccessful) {
                val body = moviesResponse.body()
                body?.let {
                    val results = it.results
                    results.forEach { movie ->
                        movie.page = it.page
                    }
                    GenericResult.Success(it.results)
                } ?: GenericResult.Error("Empty response")
            } else {
                GenericResult.Error(moviesResponse.message())
            }
        } catch (e: Exception) {
            GenericResult.Error(e.message)
        }
    }
    override suspend fun getGenres(): GenericResult<List<GenreDto>> {
        return try {
            val genresResponse = remoteDataSource.getGenres()
            if (genresResponse.isSuccessful) {
                val body = genresResponse.body()
                body?.let {
                    GenericResult.Success(it.genresList)
                } ?: GenericResult.Error("Empty response")
            }else{
                GenericResult.Error(genresResponse.message())
            }
        } catch (e: Exception) {
            GenericResult.Error(e.message)
        }
    }

}