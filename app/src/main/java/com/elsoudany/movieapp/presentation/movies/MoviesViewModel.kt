package com.elsoudany.movieapp.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsoudany.movieapp.R
import com.elsoudany.movieapp.data.repository.MoviesRepository
import com.elsoudany.movieapp.models.GenericResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    val moviesData = repository.getAllMovies()

    private val _message: MutableLiveData<Int?> = MutableLiveData()
    val message: LiveData<Int?> = _message


    fun downloadMovies(page: Int, isRefresh: Boolean = false) {
        viewModelScope.launch {
            val response = repository.downloadMovies(page, isRefresh)
            when (response) {
                is GenericResult.Error -> _message.value = R.string.error_in_retrieving_data
                is GenericResult.Success -> _message.value = R.string.loaded_page
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            repository.downloadGenres()
        }
    }

    fun messageHandled() {
        _message.value = null
    }

}