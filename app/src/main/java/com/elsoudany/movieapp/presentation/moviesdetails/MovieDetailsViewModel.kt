package com.elsoudany.movieapp.presentation.moviesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsoudany.movieapp.data.local.entity.GenreEntity
import com.elsoudany.movieapp.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _genres: MutableLiveData<List<GenreEntity>?> = MutableLiveData()
    val genres: LiveData<List<GenreEntity>?> = _genres

    fun getGenres() {
        viewModelScope.launch {
            _genres.value = repository.getAllGenres()
        }
    }

}