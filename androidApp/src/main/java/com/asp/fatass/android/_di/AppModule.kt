package com.asp.fatass.android._di

import android.app.Application
import com.asp.fatass._core.data.local.DatabaseDriverFactory
import com.asp.fatass._core.data.local.products.SqlDelightProductsDataSource
import com.asp.fatass._core.data.remote.HttpClientFactory
import com.asp.fatass._core.data.remote.products.KtorProductsClient
import com.asp.fatass._core.domain.products.ProductsClient
import com.asp.fatass._core.domain.products.ProductsDataSource
import com.asp.fatass._core.domain.products.ProductsUseCase
import com.asp.fatass.database.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModuleImpl {
//
//    @Provides
//    @Singleton
//    private fun productsDataSource(
//        app: Application
//    ): ProductsDataSource =
//        SqlDelightProductsDataSource(
//        ProductsDatabase(
//            DatabaseDriverFactory(context = app.applicationContext).createProductsDriver()
//        )
//    )
//
//    @Provides
//    @Singleton
//    private fun productsClient(): ProductsClient = KtorProductsClient(
//        HttpClientFactory().create()
//    )
//
//    @Provides
//    @Singleton
//    fun provideProductsUseCase(
//        app: Application
//    ): ProductsUseCase {
//        return ProductsUseCase(productsClient(), productsDataSource(app = app))
//    }
//}