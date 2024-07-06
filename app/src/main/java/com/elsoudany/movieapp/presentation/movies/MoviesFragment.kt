package com.elsoudany.movieapp.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.elsoudany.movieapp.databinding.FragmentMoviesBinding
import com.elsoudany.movieapp.presentation.movies.adapter.MoviesAdapter
import com.elsoudany.movieapp.presentation.movies.adapter.OnMovieClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        viewModel.getAllMovies()
        initAdapter()
        initObservers()
        return binding.root
    }

    private fun initAdapter() {
        moviesAdapter = MoviesAdapter(OnMovieClickListener {

        })
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun initObservers() {
        viewModel.moviesData.observe(viewLifecycleOwner) { moviesList ->
            moviesList?.let {
                moviesAdapter.submitList(it.toMutableList())
                viewModel.moviesDataHandled()
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMsgId ->
            errorMsgId?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.errorMsgHandled()
            }
        }
    }
}