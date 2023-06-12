package com.asp.fatass.android.products.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.asp.fatass._core.domain.util.error.AppError
import com.asp.fatass.android.R
import com.asp.fatass.products.presentation.ProductsEvent
import com.asp.fatass.products.presentation.ProductsState

@Composable
fun ProductsScreen(
    state: ProductsState,
    onEvent: (ProductsEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        val message = when(state.error) {
            is AppError.ServiceUnavailable -> context.getString(R.string.error_service_unavailable)
            is AppError.ClientError -> context.getString(R.string.client_error)
            is AppError.ServerError -> context.getString(R.string.server_error)
            is AppError.UnknownError -> context.getString(R.string.unknown_error)
            else -> null
        }
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            onEvent(ProductsEvent.ClearError)
        }
    }
}