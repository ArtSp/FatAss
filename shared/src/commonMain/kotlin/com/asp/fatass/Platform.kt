package com.asp.fatass

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform