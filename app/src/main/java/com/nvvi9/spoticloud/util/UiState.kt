package com.nvvi9.spoticloud.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val t: Throwable? = null) : UiState<Nothing>
    data object Loading : UiState<Nothing>
}

fun <T> Flow<T>.asUiState(): Flow<UiState<T>> =
    this.map<T, UiState<T>> { UiState.Success(it) }
        .onStart { emit(UiState.Loading) }
        .catch { emit(UiState.Error(it)) }
