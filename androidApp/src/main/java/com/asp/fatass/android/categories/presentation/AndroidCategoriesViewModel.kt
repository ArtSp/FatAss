package com.asp.fatass.android.categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asp.fatass._core.domain.products.ProductsUseCase
import com.asp.fatass.categories.presentation.CategoriesEvent
import com.asp.fatass.categories.presentation.CategoriesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class AndroidCategoriesViewModel @Inject constructor(
//    private val productsUseCase: ProductsUseCase
//): ViewModel() {
//
//    private val viewModel by lazy {
//        CategoriesViewModel(productsUseCase, coroutineScope = viewModelScope)
//    }
//
//    val state = viewModel.state
//
//    fun onEvent(event: CategoriesEvent) {
//        viewModel.onEvent(event)
//    }
//}