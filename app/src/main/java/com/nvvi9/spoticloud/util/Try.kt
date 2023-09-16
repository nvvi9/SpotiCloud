package com.nvvi9.spoticloud.util

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