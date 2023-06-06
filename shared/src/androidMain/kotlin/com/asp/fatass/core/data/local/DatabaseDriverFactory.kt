package com.asp.fatass.core.data.local

import android.content.Context
import com.asp.fatass.database.ProductsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createProductsDriver(): SqlDriver {
        return AndroidSqliteDriver(ProductsDatabase.Schema, context, "products.db")
    }
}