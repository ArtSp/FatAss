package com.asp.fatass.core.data.local

import android.content.Context
import com.asp.fatass.database.FatAssDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(FatAssDatabase.Schema, context, "sharedDB.db")
    }
}