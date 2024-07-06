package com.elsoudany.movieapp.presentation.movies.adapter

import com.elsoudany.movieapp.models.MovieDto

class OnMovieClickListener(val clickListener: (movie: MovieDto) -> Unit) {
    fun onClick(movie: MovieDto) = clickListener(movie)
}
