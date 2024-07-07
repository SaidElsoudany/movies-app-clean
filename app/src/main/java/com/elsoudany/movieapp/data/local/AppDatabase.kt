package com.elsoudany.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elsoudany.movieapp.data.local.dao.GenreDao
import com.elsoudany.movieapp.data.local.dao.MovieDao
import com.elsoudany.movieapp.data.local.entity.GenreEntity
import com.elsoudany.movieapp.data.local.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}