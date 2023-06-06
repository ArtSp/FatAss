package com.asp.fatass.core.data.remote.products

import com.asp.fatass.core.domain.products.ProductsClient
import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.products.models.ProductItem

class FakeProductsClient: ProductsClient {

    override suspend fun getCategories(): List<CategoryItem> {
        return (1..10).map { CategoryItem(id = it.toLong(), name = "Category $it") }
    }

    override suspend fun getProducts(
        category: CategoryItem
    ): List<ProductItem> {
        TODO("Not yet implemented")
    }
}