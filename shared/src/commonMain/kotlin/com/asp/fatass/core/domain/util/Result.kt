package com.asp.fatass.core.domain.util

sealed class Result<T>(val data: T?, val throwable: Throwable? = null) {
    class Success<T>(data: T): Result<T>(data)
    class Error<T>(throwable: Throwable): Result<T>(null, throwable)
}
