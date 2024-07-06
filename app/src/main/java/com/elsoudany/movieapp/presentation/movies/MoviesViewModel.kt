package com.elsoudany.movieapp.presentation.movies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsoudany.movieapp.MoviesApplication
import com.elsoudany.movieapp.R
import com.elsoudany.movieapp.data.repository.MoviesRepository
import com.elsoudany.movieapp.models.MovieDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _moviesData: MutableLiveData<List<MovieDto>?> = MutableLiveData()
    val moviesData: LiveData<List<MovieDto>?> = _moviesData

    private val _errorMessage: MutableLiveData<Int?> = MutableLiveData()
    val errorMessage: LiveData<Int?> = _errorMessage
    fun getAllMovies() {
        viewModelScope.launch {
            val response = repository.getAllMovies()
            if (response.isSuccessful){
                _moviesData.value = response.body()?.results
            }else{
                _errorMessage.value = R.string.error_in_retrieving_data
            }
        }
    }

    fun moviesDataHandled(){
        _moviesData.value = null
    }

    fun errorMsgHandled(){
        _errorMessage.value = null
    }
}