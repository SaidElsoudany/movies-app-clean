package com.elsoudany.movieapp.fakes

import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.models.MovieDto

object FakeMovies {

    fun getMoviesList(): List<MovieEntity> {
        return listOf(
            MovieEntity(1,"Title 1", "","2008-12-06", "1,2,3",5.89f, "overview",1),
            MovieEntity(2,"Title 2", "","2008-12-06", "1,2,3",5.89f, "overview",1),
            MovieEntity(3,"Title 3", "","2008-12-06", "1,2,3",5.89f, "overview",1),
            MovieEntity(4,"Title 4", "","2008-12-06", "1,2,3",5.89f, "overview",1),
            MovieEntity(5,"Title 5", "","2008-12-06", "1,2,3",5.89f, "overview",1),
            MovieEntity(6,"Title 6", "","2008-12-06", "1,2,3",5.89f, "overview",1),
            MovieEntity(7,"Title 7", "","2008-12-06", "1,2,3",5.89f, "overview",1),
        )
    }
}
