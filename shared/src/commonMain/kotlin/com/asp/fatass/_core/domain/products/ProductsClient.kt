package com.asp.fatass._core.domain.products

import com.asp.fatass._core.domain.products.models.CategoryItem
import com.asp.fatass._core.domain.products.models.ProductItem

interface ProductsClient {
    suspend fun getCategories(): List<CategoryItem>
    suspend fun getProducts(category: CategoryItem): List<ProductItem>
}