package com.elsoudany.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elsoudany.movieapp.data.local.entity.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre_table")
    fun getAllGenres(): List<GenreEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenres(vararg genreEntities: GenreEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresList(genreEntities: List<GenreEntity>)
}