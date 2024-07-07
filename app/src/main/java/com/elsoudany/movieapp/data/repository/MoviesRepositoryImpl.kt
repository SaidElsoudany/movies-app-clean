package com.elsoudany.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.elsoudany.movieapp.data.local.AppDatabase
import com.elsoudany.movieapp.data.local.entity.GenreEntity
import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.data.remote.MovieApiService
import com.elsoudany.movieapp.models.GenericResult
import com.elsoudany.movieapp.models.GenreDto
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.toGenreEntityList
import com.elsoudany.movieapp.toMovieEntityList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieApiService,
    private val appDatabase: AppDatabase
) : MoviesRepository {
    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        return appDatabase.movieDao().getAllMovies()
    }

    override suspend fun getAllGenres(): List<GenreEntity> {
        return appDatabase.genreDao().getAllGenres()
    }

    override suspend fun downloadMovies(pageNumber: Int, isRefresh: Boolean): GenericResult<Unit> {
        return try {
            val moviesResponse = remoteDataSource.downloadMovies(pageNumber)
            if (moviesResponse.isSuccessful) {
                val body = moviesResponse.body()
                body?.let {
                    val results = it.results
                    results.forEach { movie ->
                        movie.page = it.page
                    }
                    if (isRefresh) appDatabase.movieDao().deleteAllMovies()
                    appDatabase.movieDao().insertMoviesList(results.toMovieEntityList())
                    GenericResult.Success(Unit)
                } ?: GenericResult.Error("Empty response")
            } else {
                GenericResult.Error(moviesResponse.message())
            }
        } catch (e: Exception) {
            GenericResult.Error(e.message)
        }
    }
    override suspend fun downloadGenres(): GenericResult<Unit> {
        return try {
            val genresResponse = remoteDataSource.getGenres()
            if (genresResponse.isSuccessful) {
                val body = genresResponse.body()
                body?.let {
                    appDatabase.genreDao().insertGenresList(it.genres.toGenreEntityList())
                    GenericResult.Success(Unit)
                } ?: GenericResult.Error("Empty response")
            }else{
                GenericResult.Error(genresResponse.message())
            }
        } catch (e: Exception) {
            GenericResult.Error(e.message)
        }
    }


}