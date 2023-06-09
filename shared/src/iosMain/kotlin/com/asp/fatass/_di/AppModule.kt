package com.asp.fatass._di

import com.asp.fatass._core.domain.products.ProductsUseCase

interface AppModule {
    val productsUseCase: ProductsUseCase
}