package com.asp.fatass.di

import com.asp.fatass.core.domain.products.ProductsUseCase

interface AppModule {
    val productsUseCase: ProductsUseCase
}