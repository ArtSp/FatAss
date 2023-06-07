package com.asp.fatass._core.data.remote

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(): HttpClient
}