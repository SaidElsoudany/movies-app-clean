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
import com.elsoudany.movieapp.data.local.entity.MovieEntity
import com.elsoudany.movieapp.databinding.FragmentMovieDetailsBinding
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.presentation.moviesdetails.MovieDetailsFragmentArgs
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        updateUi(args.movie)
        initBackButtonListener()
        viewModel.getGenres()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.genres.observe(viewLifecycleOwner) { genres ->
            genres?.let {
                val genresIdString = args.movie.genresIds
                if (it.isNotEmpty() && genresIdString.isNotEmpty()) {
                    binding.genreText.visibility = View.VISIBLE
                    binding.movieGenreChipGroup.visibility = View.VISIBLE
                    val genresId = genresIdString.split(",").map { genreId ->
                        genreId.trim().toInt()
                    }
                    val genreMap = it.associate { genre -> genre.id to genre.name }
                    genresId.forEach { genreId ->
                        if (genreMap.containsKey(genreId)) {
                            val chip = Chip(requireContext())
                            chip.text = genreMap[genreId]
                            binding.movieGenreChipGroup.addView(chip)
                        }
                    }
                }else{
                    binding.genreText.visibility = View.GONE
                    binding.movieGenreChipGroup.visibility = View.GONE
                }
            }
        }
    }

    private fun initBackButtonListener() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateUi(movie: MovieEntity) {
        Glide.with(binding.root.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .centerInside()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(binding.movieImage)
        binding.movieTitle.text = movie.title
        binding.movieOverview.text = movie.overview
        binding.movieRating.rating = movie.rating / 2
    }
}