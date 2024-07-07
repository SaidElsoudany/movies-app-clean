package com.elsoudany.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.elsoudany.movieapp.data.local.entity.GenreEntity
import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.models.GenericResult
import com.elsoudany.movieapp.models.GenreDto
import com.elsoudany.movieapp.models.MovieDto

interface MoviesRepository {

    suspend fun downloadMovies(pageNumber: Int, isRefresh: Boolean): GenericResult<List<MovieDto>>
    suspend fun downloadGenres(): GenericResult<List<GenreDto>>
    fun getAllMovies(): LiveData<List<MovieEntity>>

    suspend fun getAllGenres() : List<GenreEntity>
}