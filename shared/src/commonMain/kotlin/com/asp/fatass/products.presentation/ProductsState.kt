package com.asp.fatass.products.presentation

import com.asp.fatass._core.domain.products.models.ProductItem
import com.asp.fatass._core.domain.util.error.AppError

data class ProductsState (
    val products: List<ProductItem>? = null,
    val error: AppError? = null,
    val isLoading: Set<Content> = setOf()
) {
    enum class Content {
        PRODUCTS
    }
}
