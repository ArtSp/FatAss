package com.asp.fatass.core.data.local

import com.asp.fatass.database.ProductsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(ProductsDatabase.Schema, "products.db")
    }
}