package com.elsoudany.movieapp.presentation.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elsoudany.movieapp.fakes.FakeGenres
import com.elsoudany.movieapp.fakes.FakeMovies
import com.elsoudany.movieapp.utils.MainCoroutineScopeRule
import com.elsoudany.movieapp.R
import com.elsoudany.movieapp.fakes.FakeRepository
import getValueForTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()


    @Test
    fun downloadMovies_success() = runTest {
        val fakeRepository = FakeRepository(FakeMovies.getMoviesList(), FakeGenres.getGenres())
        //Subject Under testing
        val viewModel = MoviesViewModel(fakeRepository)
        viewModel.downloadMovies(1)
        val value = viewModel.message.getValueForTest()
        assertTrue(value?.equals(R.string.loaded_page) ?: false)
    }


    @Test
    fun downloadMovies_failed() = runTest {
        val fakeRepository = FakeRepository(FakeMovies.getMoviesList(), FakeGenres.getGenres())
        fakeRepository.setReturnError(true)
        //Subject Under testing
        val viewModel = MoviesViewModel(fakeRepository)
        viewModel.downloadMovies(1)
        val value = viewModel.message.getValueForTest()
        assertTrue(value?.equals(R.string.error_in_retrieving_data) ?: false)
    }


    @Test
    fun getMoviesLiveData_success() {
        val fakeRepository = FakeRepository(FakeMovies.getMoviesList(), FakeGenres.getGenres())
        //Subject Under testing
        val viewModel = MoviesViewModel(fakeRepository)
        val movies = viewModel.moviesData.getValueForTest()
        assertTrue(movies?.size?.equals(7) ?: false)
    }

    @Test
    fun getMoviesLiveData_emptyList() {
        val fakeRepository = FakeRepository(FakeMovies.getMoviesList(), FakeGenres.getGenres())
        fakeRepository.setReturnError(true)
        //Subject Under testing
        val viewModel = MoviesViewModel(fakeRepository)
        val movies = viewModel.moviesData.getValueForTest()
        assertTrue(movies?.size?.equals(0) ?: false)
    }
}