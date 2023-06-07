package com.asp.fatass.di

import com.asp.fatass._core.data.local.DatabaseDriverFactory
import com.asp.fatass._core.data.local.products.SqlDelightProductsDataSource
import com.asp.fatass._core.data.remote.HttpClientFactory
import com.asp.fatass._core.data.remote.products.KtorProductsClient
import com.asp.fatass._core.domain.products.ProductsClient
import com.asp.fatass._core.domain.products.ProductsDataSource
import com.asp.fatass._core.domain.products.ProductsUseCase
import com.asp.fatass.database.ProductsDatabase

class AppModuleImpl: AppModule {

    private val productsDataSource: ProductsDataSource by lazy {
        SqlDelightProductsDataSource(
            ProductsDatabase(
                DatabaseDriverFactory().createProductsDriver()
            )
        )
    }

    private val productsClient: ProductsClient by lazy {
        KtorProductsClient(
            HttpClientFactory().create()
        )
    }

    override val productsUseCase: ProductsUseCase by lazy {
        ProductsUseCase(productsClient, productsDataSource)
    }
}