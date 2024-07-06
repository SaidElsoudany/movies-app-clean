package com.elsoudany.movieapp.presentation.movies.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.elsoudany.movieapp.IMAGE_BASE_URL
import com.elsoudany.movieapp.databinding.ItemMovieBinding
import com.elsoudany.movieapp.models.MovieDto

class MoviesViewHolder(private var binding: ItemMovieBinding) : ViewHolder(binding.root) {
    fun bind(currentItem: MovieDto, clickListener: OnMovieClickListener) {
        binding.movieName.text = currentItem.title
        binding.movieReleaseDate.text = currentItem.releaseDate
        Glide.with(binding.root.context)
            .load("$IMAGE_BASE_URL${currentItem.posterPath}")
            .centerCrop()
            .into(binding.movieImage)

        binding.root.setOnClickListener{
            clickListener.clickListener(currentItem)
        }
    }
}