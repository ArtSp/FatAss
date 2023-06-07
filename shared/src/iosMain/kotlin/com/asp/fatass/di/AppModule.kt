package com.asp.fatass.di

import com.asp.fatass._core.domain.products.ProductsUseCase

interface AppModule {
    val productsUseCase: ProductsUseCase
}