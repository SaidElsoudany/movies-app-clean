package com.elsoudany.movieapp.fakes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elsoudany.movieapp.data.local.entity.GenreEntity
import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.data.repository.MoviesRepository
import com.elsoudany.movieapp.models.GenericResult

class FakeRepository(private val moviesList: List<MovieEntity>,
                     private val genre: List<GenreEntity>): MoviesRepository {
    private var shouldReturnError = false

    fun setReturnError(shouldReturnError: Boolean) {
        this.shouldReturnError = shouldReturnError
    }
    override suspend fun downloadMovies(
        pageNumber: Int,
        isRefresh: Boolean
    ): GenericResult<Unit> {
        return if (shouldReturnError){
            GenericResult.Error("Error")
        }else{
            GenericResult.Success(Unit)
        }
    }

    override suspend fun downloadGenres(): GenericResult<Unit> {
        return if (shouldReturnError){
            GenericResult.Error("Error")
        }else{
            GenericResult.Success(Unit)
        }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        return if (shouldReturnError) {
            MutableLiveData(emptyList())
        }else{
            MutableLiveData(moviesList)
        }
    }

    override suspend fun getAllGenres(): List<GenreEntity> {
        return if (shouldReturnError) {
            emptyList()
        }else{
            genre
        }
    }
}