package com.asp.fatass._core.data.remote.products

import com.asp.fatass._core.domain.products.ProductsClient
import com.asp.fatass._core.domain.products.models.CategoryItem
import com.asp.fatass._core.domain.products.models.ProductItem

class FakeProductsClient: ProductsClient {

    override suspend fun getCategories(): List<CategoryItem> {
        return (1..10).map { CategoryItem(id = it.toLong(), name = "Category $it") }
    }

    override suspend fun getProducts(
        category: CategoryItem
    ): List<ProductItem> {
        return (1..20).map {
            ProductItem(
                id = it.toLong(),
                title = "Title $it",
                description = "Description for $it product",
                thumbnail = "https://picsum.photos/seed/picsum/200/300",
                price = 10.0,
                discountPercentage = null
            )
        }
    }
}