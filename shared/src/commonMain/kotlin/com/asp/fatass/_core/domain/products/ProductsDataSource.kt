package com.asp.fatass._core.domain.products

import com.asp.fatass._core.domain.products.models.CategoryItem
import com.asp.fatass._core.domain.util.flow.CommonFlow

interface ProductsDataSource {
    fun getCategories(): CommonFlow<List<CategoryItem>>
    suspend fun setCategories(items: List<CategoryItem>)
}