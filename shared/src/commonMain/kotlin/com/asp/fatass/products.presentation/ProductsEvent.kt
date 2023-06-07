package com.asp.fatass.products.presentation

import com.asp.fatass.core.domain.products.models.CategoryItem

sealed class ProductsEvent {
    data class LoadContent(val category: CategoryItem): ProductsEvent()
    object ClearError: ProductsEvent()
}