package com.asp.fatass.core.domain.util.error

sealed class AppError(private val _text: String): Throwable(_text) {
    data class CLIENT_ERROR(val text: String): AppError(text)
    data class SERVER_ERROR(val text: String): AppError(text)
    object UNKNOWN_ERROR: AppError("")
}