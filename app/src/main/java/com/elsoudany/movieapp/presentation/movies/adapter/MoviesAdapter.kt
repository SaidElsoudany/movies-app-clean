package com.elsoudany.movieapp.presentation.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.elsoudany.movieapp.databinding.ItemMovieBinding
import com.elsoudany.movieapp.models.MovieDto

class MoviesAdapter(private val clickListener: OnMovieClickListener) :
    ListAdapter<MovieDto, MoviesViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<MovieDto>() {
        override fun areItemsTheSame(
            oldItem: MovieDto,
            newItem: MovieDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MovieDto,
            newItem: MovieDto
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = currentList[position]
        currentItem?.let {
            holder.bind(currentItem, clickListener)
        }
    }
}