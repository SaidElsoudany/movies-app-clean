package com.elsoudany.movieapp.presentation.moviesdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elsoudany.movieapp.fakes.FakeGenres
import com.elsoudany.movieapp.fakes.FakeMovies
import com.elsoudany.movieapp.utils.MainCoroutineScopeRule
import com.elsoudany.movieapp.fakes.FakeRepository
import getValueForTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    @Test
    fun getGenres_success() = runTest {
        val fakeRepository = FakeRepository(FakeMovies.getMoviesList(), FakeGenres.getGenres())
        //Subject Under testing
        val viewModel = MovieDetailsViewModel(fakeRepository)
        viewModel.getGenres()
        val value = viewModel.genres.getValueForTest()
        TestCase.assertTrue(value?.size?.equals(6) ?: false)
    }

    @Test
    fun getGenres_failed() = runTest {
        val fakeRepository = FakeRepository(FakeMovies.getMoviesList(), FakeGenres.getGenres())
        fakeRepository.setReturnError(true)
        //Subject Under testing
        val viewModel = MovieDetailsViewModel(fakeRepository)
        viewModel.getGenres()
        val value = viewModel.genres.getValueForTest()
        TestCase.assertTrue(value?.size?.equals(0) ?: false)
    }
}