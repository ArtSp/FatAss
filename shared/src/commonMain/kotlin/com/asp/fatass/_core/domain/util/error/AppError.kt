package com.asp.fatass._core.domain.util.error

sealed class AppError(private val _message: String): Throwable(_message) {
    data class ClientError(override val message: String): AppError(message)
    data class ServerError(override val message: String): AppError(message)
    object UnknownError: AppError("")
    object ServiceUnavailable: AppError("")
}