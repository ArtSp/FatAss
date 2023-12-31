package com.asp.fatass._core.data.local

import com.asp.fatass.database.ProductsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createProductsDriver(): SqlDriver {
        return NativeSqliteDriver(ProductsDatabase.Schema, "products.db")
    }
}