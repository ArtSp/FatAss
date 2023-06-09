package com.asp.fatass.android.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asp.fatass._core.domain.products.ProductsUseCase
import com.asp.fatass.products.presentation.ProductsEvent
import com.asp.fatass.products.presentation.ProductsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AndroidProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
): ViewModel() {

    private val viewModel by lazy {
        ProductsViewModel(productsUseCase, coroutineScope = viewModelScope)
    }

    val state = viewModel.state

    fun onEvent(event: ProductsEvent) {
        viewModel.onEvent(event)
    }
}