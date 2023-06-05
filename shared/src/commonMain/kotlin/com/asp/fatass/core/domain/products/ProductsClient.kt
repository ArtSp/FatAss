package com.asp.fatass.core.domain.products

import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.products.models.ProductItem

interface ProductsClient {
    suspend fun getCategories(): List<CategoryItem>
    suspend fun getProducts(category: CategoryItem): List<ProductItem>
}