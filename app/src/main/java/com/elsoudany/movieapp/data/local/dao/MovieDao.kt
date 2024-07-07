package com.elsoudany.movieapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elsoudany.movieapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table ORDER BY page ASC")
    fun getAllMovies(): LiveData<List<MovieEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(vararg moviesEntities: MovieEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesList(moviesEntities: List<MovieEntity>)
    @Delete
    fun deleteMovie(movieEntity: MovieEntity)
    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()
}