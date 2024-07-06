package com.elsoudany.movieapp.presentation.moviesdetails

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elsoudany.movieapp.R
import com.elsoudany.movieapp.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater,container, false)
        return binding.root
    }
}