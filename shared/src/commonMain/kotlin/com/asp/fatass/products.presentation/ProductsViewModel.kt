package com.asp.fatass.products.presentation

import com.asp.fatass._core.domain.products.ProductsUseCase
import com.asp.fatass._core.domain.products.models.CategoryItem
import com.asp.fatass._core.domain.util.Resource
import com.asp.fatass._core.domain.util.error.AppException
import com.asp.fatass._core.domain.util.flow.toCommonStateFlow
import com.asp.fatass._core.presentation.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productsUseCase: ProductsUseCase,
    coroutineScope: CoroutineScope?
) : AppViewModel<ProductsState, ProductsEvent>(coroutineScope) {

    private var getProductsJob: Job? = null

    private val _state = MutableStateFlow(ProductsState())
    override val state = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductsState())
        .toCommonStateFlow()

    override fun onEvent(
        event: ProductsEvent
    ) {
        when (event) {
            is ProductsEvent.LoadContent -> {
                loadProducts(event.category)
            }

            is ProductsEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
        }
    }

    private fun loadProducts(
        category: CategoryItem
    ) {
        getProductsJob?.cancel()
        getProductsJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = it.isLoading.plus(ProductsState.Content.PRODUCTS)
                )
            }

            val result = productsUseCase.getProducts(category = category)

            when(result) {
                is Resource.Success -> {
                    _state.update { it.copy(
                        products = result.data,
                        isLoading = it.isLoading.minus(ProductsState.Content.PRODUCTS),
                        error = null
                    ) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(
                        isLoading = it.isLoading.minus(ProductsState.Content.PRODUCTS),
                        error = (result.throwable as? AppException)?.error
                    ) }
                }
            }
        }
    }
}


