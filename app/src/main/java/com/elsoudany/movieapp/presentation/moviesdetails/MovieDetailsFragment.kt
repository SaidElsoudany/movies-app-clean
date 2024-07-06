package com.elsoudany.movieapp.presentation.moviesdetails

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.elsoudany.movieapp.IMAGE_BASE_URL
import com.elsoudany.movieapp.R
import com.elsoudany.movieapp.databinding.FragmentMovieDetailsBinding
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.presentation.moviesdetails.MovieDetailsFragmentArgs
import com.google.android.material.chip.Chip

class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args : MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater,container, false)
        updateUi(args.movie)
        initBackButtonListener()
        return binding.root
    }

    private fun initBackButtonListener() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateUi(movie: MovieDto) {
        Glide.with(binding.root.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .centerInside()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(binding.movieImage)
        binding.movieTitle.text = movie.title
        binding.movieOverview.text = movie.overview
        binding.movieRating.rating = movie.rating / 2

        val chip1 = Chip(requireContext())
        chip1.text = "Action"
        val chip2 = Chip(requireContext())
        chip2.text = "Comedy"
        binding.movieGenreChipGroup.addView(chip1)
        binding.movieGenreChipGroup.addView(chip2)
    }
}