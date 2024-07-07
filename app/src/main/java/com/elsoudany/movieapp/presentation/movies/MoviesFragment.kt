package com.elsoudany.movieapp.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elsoudany.movieapp.databinding.FragmentMoviesBinding
import com.elsoudany.movieapp.models.MovieDto
import com.elsoudany.movieapp.presentation.movies.adapter.MoviesAdapter
import com.elsoudany.movieapp.presentation.movies.adapter.OnMovieClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGenres()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        initAdapter()
        initObservers()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.downloadMovies(1, true)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initAdapter() {
        moviesAdapter = MoviesAdapter(OnMovieClickListener { movie ->
            findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
                    movie
                )
            )
        })
        binding.moviesRv.adapter = moviesAdapter
        binding.moviesRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (moviesAdapter.currentList.size > 0) {
                        val page = moviesAdapter.currentList.last()?.page
                        page?.let {
                            viewModel.downloadMovies(page + 1)
                        }
                    }
                }
            }
        })
    }

    private fun initObservers() {
        viewModel.moviesData.observe(viewLifecycleOwner) { moviesList ->
            moviesList?.let {
                if (moviesList.isNotEmpty()) {
                    binding.moviesRv.visibility = View.VISIBLE
                    binding.emptyListText.visibility = View.GONE
                    moviesAdapter.submitList(it.toMutableList())
                }else{
                    binding.moviesRv.visibility = View.GONE
                    binding.emptyListText.visibility = View.VISIBLE
                    viewModel.downloadMovies(1)
                }
            }
        }
        viewModel.message.observe(viewLifecycleOwner)
        { messageId ->
            messageId?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.messageHandled()
            }
        }
    }
}