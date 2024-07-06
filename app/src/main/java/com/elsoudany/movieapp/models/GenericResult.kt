package com.elsoudany.movieapp.models

import java.lang.Exception

sealed class GenericResult<out T> {
    data class Success<T>(val data: T): GenericResult<T>()
    data class Error(val message: String?) : GenericResult<Nothing>()

}