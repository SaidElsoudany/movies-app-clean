package com.elsoudany.movieapp.presentation.movies.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.elsoudany.movieapp.databinding.ItemMovieBinding
import com.elsoudany.movieapp.models.MovieDto

class MoviesViewHolder(private var binding: ItemMovieBinding) : ViewHolder(binding.root) {
    fun bind(currentItem: MovieDto, clickListener: OnMovieClickListener) {
        binding.movieName.text = currentItem.title
        binding.movieReleaseDate.text = currentItem.releaseDate
        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w500/${currentItem.posterPath}")
            .centerCrop()
            .into(binding.movieImage)
        binding.root.setOnClickListener{
            clickListener.clickListener(currentItem)
        }
    }
}