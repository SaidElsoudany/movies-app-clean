package com.elsoudany.movieapp.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsoudany.movieapp.R
import com.elsoudany.movieapp.data.repository.MoviesRepository
import com.elsoudany.movieapp.models.GenericResult
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
    fun downloadMovies(page: Int) {
        viewModelScope.launch {
            when(val response = repository.downloadMovies(page)) {
                is GenericResult.Success -> _moviesData.value = response.data
                is GenericResult.Error -> _errorMessage.value = R.string.error_in_retrieving_data
            }
        }
    }
    fun getGenres(){
        viewModelScope.launch {
            repository.getGenres()
        }
    }
    fun moviesDataHandled(){
        _moviesData.value = null
    }

    fun errorMsgHandled(){
        _errorMessage.value = null
    }

}