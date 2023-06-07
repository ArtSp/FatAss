package com.asp.fatass._core.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createProductsDriver(): SqlDriver
}