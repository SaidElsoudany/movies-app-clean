package com.elsoudany.movieapp.presentation.movies.adapter

import com.elsoudany.movieapp.data.local.entity.MovieEntity

class OnMovieClickListener(val clickListener: (movie: MovieEntity) -> Unit) {
    fun onClick(movie: MovieEntity) = clickListener(movie)
}
