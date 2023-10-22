package com.nvvi9.spoticloud.util

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val t: Throwable? = null) : UiState<Nothing>
    data object Loading : UiState<Nothing>
}
