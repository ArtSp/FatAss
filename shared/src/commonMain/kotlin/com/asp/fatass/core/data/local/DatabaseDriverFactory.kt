package com.asp.fatass.core.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createProductsDriver(): SqlDriver
}