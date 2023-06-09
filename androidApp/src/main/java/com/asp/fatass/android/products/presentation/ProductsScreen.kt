package com.asp.fatass.android.products.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.asp.fatass.products.presentation.ProductsEvent
import com.asp.fatass.products.presentation.ProductsState

@Composable
fun ProductsScreen(
    state: ProductsState,
    onEvent: (ProductsEvent) -> Unit
) {
    val context = LocalContext.current
}