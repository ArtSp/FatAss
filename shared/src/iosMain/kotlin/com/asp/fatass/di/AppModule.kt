package com.asp.fatass.di

import com.asp.fatass.core.data.local.DatabaseDriverFactory
import com.asp.fatass.core.data.local.products.SqlDelightProductsDataSource
import com.asp.fatass.core.data.remote.HttpClientFactory
import com.asp.fatass.core.data.remote.products.KtorProductsClient
import com.asp.fatass.core.domain.products.ProductsClient
import com.asp.fatass.core.domain.products.ProductsDataSource
import com.asp.fatass.core.domain.products.ProductsUseCase
import com.asp.fatass.database.ProductsDatabase

class AppModule {

    private val productsDataSource: ProductsDataSource by lazy {
        SqlDelightProductsDataSource(
            ProductsDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val productsClient: ProductsClient by lazy {
        KtorProductsClient(
            HttpClientFactory().create()
        )
    }

    val productsUseCase: ProductsUseCase by lazy {
        ProductsUseCase(productsClient, productsDataSource)
    }
}