package com.elsoudany.movieapp.presentation.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.databinding.ItemMovieBinding

class MoviesAdapter(private val clickListener: OnMovieClickListener) :
    ListAdapter<MovieEntity, MoviesViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieEntity,
            newItem: MovieEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MovieEntity,
            newItem: MovieEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem, clickListener)
        }
    }
}