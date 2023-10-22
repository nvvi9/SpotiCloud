package com.nvvi9.spoticloud.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Try<out T> {
    data class Success<T>(val data: T) : Try<T>
    data class Failure(val t: Throwable) : Try<Nothing>
}

inline fun <T, R> Try<T>.map(transform: (T) -> R): Try<R> =
    flatMap { runSafely { transform(it) } }

@Suppress("UNCHECKED_CAST")
inline fun <T, R> Try<T>.flatMap(transform: (T) -> Try<R>): Try<R> =
    fold({ transform(it) }, { this as Try<R> })

inline val <T> Try<T>.valueOrNull: T?
    get() = this.fold({ it }, { null })

inline fun <T, R> Try<T>.fold(onSuccess: (T) -> R, onFailure: (Throwable) -> R): R =
    when (this) {
        is Try.Failure -> onFailure(t)
        is Try.Success -> onSuccess(data)
    }

fun <T> Result<T>.asTry(): Try<T> =
    this.fold({ Try.Success(it) }, { Try.Failure(it) })

inline fun <T> runSafely(action: () -> T): Try<T> = try {
    Try.Success(action())
} catch (t: Throwable) {
    Try.Failure(t)
}

fun <T> List<Try<T>>.unzip(): Try<List<T>> {
    val result = mutableListOf<T>()
    for (tryValue in this) {
        when (tryValue) {
            is Try.Failure -> return tryValue
            is Try.Success -> result.add(tryValue.data)
        }
    }
    return Try.Success(result)
}

fun <T> Flow<Try<T>>.asUiStateFlow(): Flow<UiState<T>> =
    this.map { tryValue -> tryValue.fold({ UiState.Success(it) }, { UiState.Error() }) }
        .onStart { emit(UiState.Loading) }
        .catch { emit(UiState.Error()) }
